package org.auto.plate.controller;

import org.auto.plate.entity.AutoCase;
import org.auto.plate.entity.RespBean;
import org.auto.plate.service.AutoCaseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (AutoCase)表控制层
 *
 * @author makejava
 * @since 2020-04-18 23:34:05
 */
@RestController
@RequestMapping("api")
public class AutoCaseController {
    /**
     * 服务对象
     */
    @Resource
    private AutoCaseService autoCaseService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("findById")
    public RespBean selectOne(Integer id) {
        return RespBean.ok("获取成功！！！",this.autoCaseService.queryById(id));
    }

    /**
     * 添加模板
     * @param autoCase
     * @return
     */
    @RequestMapping(value = "/addAutoTestCase",method = RequestMethod.POST)
    public RespBean addAutoTestCase(@RequestBody AutoCase autoCase) {
        return autoCaseService.addAutoTestCase(autoCase);
    }

    /**
     * 根据用户Id分页获取caselist
     * @param query
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/templateOrCaseListByUser")
    public RespBean getAllAutoTestCase(@RequestParam String query,
                                       Integer userId, Integer pageNum, Integer pageSize, Integer type) {
        return autoCaseService.getAutoCaseOrTemplateListByUserId(query,userId,pageNum,pageSize,type);
    }

    /**
     * 根据projectId分页获取caseList
     * @param query
     * @param projectId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/templateOrCaseListByPro")
    public RespBean getAllAutoTestCaseByProjectId(@RequestParam String query,
                                       Integer projectId, Integer pageNum, Integer pageSize, Integer type) {
        return autoCaseService.getAllAutoTestCaseByProjectId(query, projectId, pageNum, pageSize, type);
    }

    @GetMapping("/getAutoCaseCount")
    public RespBean getAutoCaseCountByProId(@RequestParam Integer projectId){
        return autoCaseService.getAutoCaseCountByProId(projectId);
    }

    /**
     * 根据项目id获取所有case和template
     * @param projectId
     * @return
     */
    @GetMapping("/allCaseOrTemplate")
    public RespBean getAutoCaseByProId(@RequestParam Integer projectId, Integer type){
        return autoCaseService.getAutoCaseOrTemplateProId(projectId, type);
    }

    @GetMapping("/getAutoTestCaseCountList")
    public RespBean getAutoTestCaseCountList(@RequestParam Integer userId,Integer type) {
        return autoCaseService.getAutoTestCaseCountList(userId, type);
    }

    @RequestMapping(value = "/deleteAutoCase", method = RequestMethod.POST)
    public RespBean deleteAutoCase(@RequestBody List<AutoCase> autoCaseList) {
        boolean flag = autoCaseService.delete(autoCaseList);
        if (flag) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
}