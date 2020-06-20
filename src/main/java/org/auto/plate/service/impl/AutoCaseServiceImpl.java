package org.auto.plate.service.impl;

import org.auto.plate.entity.AutoCase;
import org.auto.plate.entity.AutoCaseListObj;
import org.auto.plate.entity.AutoProject;
import org.auto.plate.entity.RespBean;
import org.auto.plate.mapper.AutoCaseMapper;
import org.auto.plate.service.AutoCaseService;
import org.auto.plate.service.AutoProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (AutoCase)表服务实现类
 *
 * @author makejava
 * @since 2020-04-18 23:34:05
 */
@Service("autoCaseService")
public class AutoCaseServiceImpl implements AutoCaseService {
    @Resource
    private AutoCaseMapper autoCaseMapper;

    @Resource
    private AutoProjectService autoProjectService;

    @Resource
    AutoCaseListObj autoCaseListObj;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AutoCase queryById(Integer id) {
        return this.autoCaseMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<AutoCase> queryAllByLimit(int offset, int limit) {
        return this.autoCaseMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param autoCase 实例对象
     * @return 实例对象
     */
    @Override
    public AutoCase insert(AutoCase autoCase) {
        this.autoCaseMapper.insert(autoCase);
        return autoCase;
    }

    /**
     * 修改数据
     *
     * @param autoCase 实例对象
     * @return 实例对象
     */
    @Override
    public AutoCase update(AutoCase autoCase) {
        this.autoCaseMapper.update(autoCase);
        return this.queryById(autoCase.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.autoCaseMapper.deleteById(id) > 0;
    }

    /**
     * 添加自动化测试用例case
     * @param autoCase
     * @return
     */
    @Override
    public RespBean addAutoTestCase(AutoCase autoCase) {
        AutoProject autoProject = autoProjectService.queryById(autoCase.getProid());
        autoCase.setProname(autoProject.getProjectname());
        Date date = new Date();
        autoCase.setCreatetime(date);
        autoCase.setStatus(1);
        Integer count = autoCaseMapper.insert(autoCase);
        AutoCase autoCase1 = autoCaseMapper.selectAutoCaseByCreateDate(date);
        if (count != 1) {
            return RespBean.error("添加失败！！！");
        }
        return RespBean.ok("添加成功！！！",autoCase1);
    }

    /**
     * 获取caselist 或者 templates
     * @param query
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public RespBean getAutoCaseOrTemplateListByUserId(String query, Integer userId, Integer pageNum, Integer pageSize, Integer type) {
        int firstNum = (pageNum-1) * pageSize;
        List<AutoCase> list = autoCaseMapper.selectAllAutoCaseByUser(query,userId,firstNum,pageSize, type);
        autoCaseListObj.setAutoCaseList(list);
        autoCaseListObj.setTotal(autoCaseMapper.queryAllCase(query, userId, type).size());
        if (autoCaseListObj.getTotal() == 0) {
            return RespBean.error("暂无用例！！！");
        }
        return RespBean.ok("获取成功！！！",autoCaseListObj);
    }

    /**
     * 获取项目下的所有template
     * @param projectId
     * @return
     */
    @Override
    public RespBean getAutoCaseOrTemplateProId(Integer projectId, Integer type) {
        List<AutoCase> list = autoCaseMapper.selectAllByProId(projectId, type);
        return RespBean.ok("获取成功！！！",list);
    }

    @Override
    public RespBean getAutoCaseCountByProId(Integer projectId) {
        int count = autoCaseMapper.selectAllAutoCaseByProId(projectId);
        return RespBean.ok("获取成功！！！",count);
    }

    @Override
    public RespBean getAutoTestCaseCountList(Integer userId, Integer type) {
        List<Integer> autoCaseCountList = new ArrayList<Integer>();
        List<AutoProject> list = autoProjectService.findUserProList(userId);
        for (int i = 0; i < list.size(); i++) {
            autoCaseCountList.add(autoCaseMapper.selectCaseCount(list.get(i).getId(),type));
        }
        return RespBean.ok("获取成功！！！",autoCaseCountList);
    }

    /**
     * 待完善
     * @param autoCaseList
     * @return
     */
    @Override
    public boolean delete(List<AutoCase> autoCaseList) {
        List<Integer> autoCaseIdList = new ArrayList<Integer>();
        for (int i = 0; i < autoCaseList.size(); i++) {
            autoCaseIdList.add(autoCaseList.get(i).getId());
        }
        for (int i = 0; i < autoCaseIdList.size(); i++) {
            autoCaseMapper.deleteById(autoCaseIdList.get(i));
        }
        return true;
    }

    @Override
    public RespBean getAllAutoTestCaseByProjectId(String query, Integer projectId, Integer pageNum, Integer pageSize, Integer type) {
        int firstNum = (pageNum-1) * pageSize;
        autoCaseListObj.setAutoCaseList(autoCaseMapper.selectAllAutoCaseByProject(query,projectId,firstNum,pageSize, type));
        autoCaseListObj.setTotal(autoCaseMapper.queryAllCaseCountByProject(projectId,type));
        if (autoCaseListObj.getTotal() == 0) {
            return RespBean.error("暂无用例！！！");
        }
        return RespBean.ok("获取成功！！！",autoCaseListObj);
    }
}