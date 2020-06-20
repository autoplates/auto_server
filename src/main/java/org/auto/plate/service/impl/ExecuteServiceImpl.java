package org.auto.plate.service.impl;

import org.apache.log4j.Logger;
import org.auto.plate.entity.*;
import org.auto.plate.exception.DefineException;
import org.auto.plate.mapper.AutoTaskMapper;
import org.auto.plate.service.*;
import org.auto.plate.utils.ElementOperate;
import org.auto.plate.utils.impl.ElementOperateIml;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExecuteServiceImpl implements ExecuteService {

    private static Logger logger = Logger.getLogger(ElementOperateIml.class);

    @Resource
    AutoCaseService autoCaseService;
    @Resource
    AutoProjectService autoProjectService;
    @Resource
    AutoElementService autoElementService;
    @Resource
    ElementOperate elementOperate;
    @Resource
    AutoTaskService autoTaskService;
    @Resource
    AutoTaskMapper autoTaskMapper;

    /**
     * 执行用例
     * @param caseId
     * @param model
     * @return
     */
    @Override
    public RespBean execute(Integer caseId, Integer model, String browserType){
        //  获取case_execute_log对象
        ExecuteCaseLog executeCaseLog = new ExecuteCaseLog();
        executeCaseLog.setExcuteCaseLogList(new ArrayList<String>());
        //  根据caseId查询要执行的case
        AutoCase autoCase = autoCaseService.queryById(caseId);
        //  获取case所处的项目
        AutoProject autoProject = autoProjectService.queryById(autoCase.getProid());
        //  获取elementlist
        List<AutoElement> elementList = getExecuteCaseElementList(autoCase);
        //  获取paramlist
        List<String> paramList = getExecuteCaseParamList(autoCase);

        logger.debug("auto_test_name:"+autoCase.getCasename()+",auto_test_step_num:"+elementList.size()+",auto_params:"+paramList.toString());
        executeCaseLog.getExecuteCaseLogList().add(this.getCurrentTime() + "auto_test_name:"+autoCase.getCasename()+",auto_test_step_num:"+elementList.size()+",auto_params:"+paramList.toString() + "</br></br>");
        try {
            elementOperate.excuteTestCase(autoProject.getProjectaddress(),elementList,paramList, model, executeCaseLog, browserType);
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("系统异常错误！！！");
        }
        //  根据case执行结果，做后置处理
        RespBean result1 = getRespBean(autoCase, executeCaseLog);
        if (result1 != null) return result1;
        return RespBean.error("系统异常错误！！！");
    }

    /**
     * 获取paramlist
     * @param autoCase
     * @return
     */
    private List<String> getExecuteCaseParamList(AutoCase autoCase) {
        //  获取参数list
        List<String> paramList = new ArrayList<String>();
        //  获取case前置模板的参数拼接字符串
        String preTemplateParamStr = this.getTemplateParamStr(autoCase.getPretemplate());
        //  获取case后置模板的参数拼接字符串
        String postTemplateParamStr = this.getTemplateParamStr(autoCase.getPosttemplate());
        //  获取整条case 的param的拼接字符串
        String paramStr = preTemplateParamStr + autoCase.getParamslist() + postTemplateParamStr;
        //  切割获取参数拼接字符串，获取param数组
        String[] paramArray = paramStr.split(",");
        //  循环插入进list集合
        for (int i = 0; i < paramArray.length; i++) {
            paramList.add(paramArray[i]);
        }
        return paramList;
    }

    /**
     * 获取elementlist
     * @param autoCase
     * @return
     */
    private List<AutoElement> getExecuteCaseElementList(AutoCase autoCase) {
        //  获取elementList
        List<AutoElement> elementList = new ArrayList<AutoElement>();
        // 获取case前置模板的elementId拼接字符串
        String preTemplateElementIdStr = this.getTemplateElementStr(autoCase.getPretemplate());
        // 获取case后置模板的elementId拼接字符串
        String postTemplateElementIdStr = this.getTemplateElementStr(autoCase.getPosttemplate());
        //  获取整条case 的elementId拼接字符串
        String elementIdStr = preTemplateElementIdStr + autoCase.getElelist() + postTemplateElementIdStr;
        // 切割获取元素id数组
        String[] elementIdArray = elementIdStr.split(",");
        //  循环获取元素element，插入到list集合
        for (int i = 0; i < elementIdArray.length; i++) {
            elementList.add(autoElementService.queryById(Integer.parseInt(elementIdArray[i])));
        }
        return elementList;
    }

    /**
     * 获取参数拼接字符串
     * @param template_list
     * @return
     */
    private String getTemplateParamStr(String template_list) {
        if (template_list == null || template_list.equals("")) {
            return "";
        }
        List<AutoCase> autoCaseList = new ArrayList<AutoCase>();
        String strlist = "";
        boolean flag = true;
        String[] templateArray = template_list.split(",");
        //  循环获取各模板
        for (int i = 0; i < templateArray.length; i++) {
            autoCaseList.add(autoCaseService.queryById(Integer.parseInt(templateArray[i])));
        }
        //  循环获取elementlist
        for (int i = 0; i < autoCaseList.size(); i++) {
            strlist += autoCaseList.get(i).getParamslist();
        }
        return strlist;
    }

    /**
     * 获取elementId拼接字符串
     * @param template_caseId_List
     * @return
     */
    private String getTemplateElementStr(String template_caseId_List) {
        if (template_caseId_List == null || template_caseId_List.equals("")) {
            return "";
        }
        List<AutoCase> autoCaseList = new ArrayList<AutoCase>();
        String strlist = "";
        String[] templateArray = template_caseId_List.split(",");
        //  循环获取各模板
        for (int i = 0; i < templateArray.length; i++) {
            autoCaseList.add(autoCaseService.queryById(Integer.parseInt(templateArray[i])));
        }
        //  循环获取elementlist
        for (int i = 0; i < autoCaseList.size(); i++) {
            strlist += autoCaseList.get(i).getElelist();
        }
        return strlist;
    }

    /**
     * 执行用例
     * @param autoCase
     * @return
     */
    @Override
    public RespBean executeCase(AutoCase autoCase, String browserType) {
        ExecuteCaseLog executeCaseLog = new ExecuteCaseLog();
        executeCaseLog.setExcuteCaseLogList(new ArrayList<String>());
        int model = 0;
        List<Integer> eleIdList = new ArrayList<Integer>();
        List<AutoElement> elementList = new ArrayList<AutoElement>();
        List<String> paramList = new ArrayList<String>();
        AutoProject autoProject = autoProjectService.queryById(autoCase.getProid());
        String eleList = autoCase.getElelist();
        String[] eleIdstr = eleList.split(",");
        String[] paramStr = autoCase.getParamslist().split(",");
        for (int i = 0; i < eleIdstr.length; i++) {
            eleIdList.add(Integer.parseInt(eleIdstr[i]));
        }
        for (int i = 0; i < eleIdList.size(); i++) {
            elementList.add(autoElementService.queryById(eleIdList.get(i)));
        }
        for (int i = 0; i < paramStr.length; i++) {
            paramList.add(paramStr[i]);
        }
        logger.debug("auto_test_name:"+autoCase.getCasename()+",auto_test_step_num:"+elementList.size()+",auto_params:"+paramList.toString());
        executeCaseLog.getExecuteCaseLogList().add(this.getCurrentTime() + "auto_test_name:"+autoCase.getCasename()+",auto_test_step_num:"+elementList.size()+",auto_params:"+paramList.toString() + "</br></br>");
        ExecuteCaseLog executeCaseLogs = null;
        try {
            executeCaseLogs = elementOperate.excuteTestCase(autoProject.getProjectaddress(),elementList,paramList, model,executeCaseLog, browserType);
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("auto_test_case执行异常！！！");
        }
        //  返回执行日志 和 执行结果状态
        RespBean result1 = getRespBean(autoCase, executeCaseLogs);
        if (result1 != null) return result1;
        return RespBean.error("服务器错误!!!,请联系维护人员");
    }

    /**
     * 执行task
     * @param taskId
     * @param model
     * @param browserType
     * @return
     */
    @Override
    public RespBean executeTask(Integer taskId, Integer model, String browserType) {
        Date currentTime = new Date();
        List<RespBean> respBeans = new ArrayList<RespBean>();
        List<AutoCase> autoCaseList = new ArrayList<AutoCase>();
        AutoTask autoTask = autoTaskService.getAutoTaskById(taskId);
        String caselist = autoTask.getCaseList();
        String[] caseIdArray = caselist.split(",");
        for (int i = 0; i < caseIdArray.length; i++) {
            RespBean respBean = this.execute(Integer.parseInt(caseIdArray[i]),model,browserType);
            if (((AutoCase)respBean.getObj()).getExecutelog().contains("ERROR") || ((AutoCase)respBean.getObj()).getExecutelog().contains("FAIL")) {
                autoCaseList.add(autoCaseService.queryById(Integer.parseInt(caseIdArray[i])));
                respBeans.add(respBean);
            }
        }
        String res = "";
        for (int i = 0; i < respBeans.size(); i++) {
            res += ((AutoCase)respBeans.get(i).getObj()).getExecutelog() + ",";
        }
        if (!res.contains("ERROR") && !res.contains("FAIL")){
            autoTask.setStatus(2);
            autoTask.setExecutetime(currentTime);
            autoTask.setSuccess(autoTask.getSuccess() + 1);
            autoTaskMapper.update(autoTask);
            System.out.println("执行了.................................................................");
            return RespBean.ok("执行成功");
        }
        if (res.contains("ERROR") && res.contains("FAIL")){
            String msg = "执行错误！！！，错误case和失败case如下：</br>";
            for (int i = 0; i < autoCaseList.size(); i++) {
                msg += autoCaseList.get(i).getCasename() + "</br>";
            }
            autoTask.setStatus(4);
            autoTask.setExecutetime(currentTime);
            autoTask.setFail(autoTask.getFail() + 1);
            System.out.println("执行了.................................................................");
            autoTaskMapper.update(autoTask);
            return RespBean.error(msg,res,autoCaseList);
        } else if(res.contains("FAIL")){
            String msgs = "执行失败！！！，失败case如下：</br>";
            for (int i = 0; i < autoCaseList.size(); i++) {
                msgs += autoCaseList.get(i).getCasename() + "</br>";
            }
            autoTask.setStatus(3);
            autoTask.setExecutetime(currentTime);
            autoTask.setFail(autoTask.getFail() + 1);
            autoTaskMapper.update(autoTask);
            System.out.println("执行了.................................................................");
            return RespBean.error(msgs,res,autoCaseList);
        }
        return RespBean.error("系统错误！！！",null);
    }

    /**
     * 返回执行日志 和 执行结果
     * @param autoCase
     * @param executeCaseLog
     * @return
     */
    private RespBean getRespBean(AutoCase autoCase, ExecuteCaseLog executeCaseLog) {
        String result = "";
        for (int i = 0; i < executeCaseLog.getExecuteCaseLogList().size(); i++) {
            result += executeCaseLog.getExecuteCaseLogList().get(i);
        }
        autoCase.setExecutelog(result);
        if (result.contains("ERROR")) {
            autoCaseExecuteEndDeal(autoCase, 4);
            return RespBean.error("执行错误!!!", autoCase);
        }else if (result.contains("FAIL")) {
            autoCaseExecuteEndDeal(autoCase, 3);
            return RespBean.error("执行失败!!!", autoCase);
        }else if (result.contains("SUCCESS")) {
            autoCaseExecuteEndDeal(autoCase, 2);
            return RespBean.ok("执行成功!!!", autoCase);
        }
        autoCaseExecuteEndDeal(autoCase, 2);
        return RespBean.ok("执行成功!!!", autoCase);
    }

    /**
     * case执行结束处理逻辑
     * @param autoCase
     * @param i2
     */
    private void autoCaseExecuteEndDeal(AutoCase autoCase, int i2) {
        autoCase.setStatus(i2);
        autoCase.setExecutetime(new Date());
        autoCaseService.update(autoCase);
    }

    private String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        return "[" + dateFormat.format(date) + "]";
    }


}
