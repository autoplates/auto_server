package org.auto.plate.service.impl;

import org.auto.plate.entity.AutoPage;
import org.auto.plate.entity.AutoProject;
import org.auto.plate.entity.RespBean;
import org.auto.plate.mapper.AutoPageMapper;
import org.auto.plate.mapper.AutoProjectMapper;
import org.auto.plate.mapper.AutoUserProMapper;
import org.auto.plate.service.AutoProjectService;
import org.springframework.stereotype.Service;
import org.auto.plate.entity.AutoUserPro;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
@Service
public class AutoProjectServiceImpl implements AutoProjectService {

    @Resource
    AutoProjectMapper autoProjectMapper;
    @Resource
    AutoUserProMapper autoUserProMapper;
    @Resource
    AutoPageMapper autoPageMapper;

    AutoUserPro autoUserPro = new AutoUserPro();

    @Override
    public AutoProject queryById(Integer id) {
        AutoProject autoProject = autoProjectMapper.queryById(id);
        return autoProject;
    }

    @Override
    public List<AutoProject> queryAllByLimit(int offset, int limit) {
        return null;
    }

    @Override
    public AutoProject insert(AutoProject autoProject) {
        return null;
    }

    @Override
    public AutoProject update(AutoProject autoProject) {
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    @Override
    public RespBean getProjectList(String query,Integer projectType, Integer userId, Integer pageSize, Integer pageNum) {
        int firstNum = (pageNum-1) * pageSize;
        List<AutoProject> autoProjectList = autoProjectMapper.selectAllProjectList(query,projectType,userId,firstNum,pageSize);
        if(autoProjectList.size() ==0) {
            return RespBean.error("暂无数据",autoProjectList);
        }
        return RespBean.ok("获取成功",autoProjectList);
    }

    @Override
    public RespBean addProject(AutoProject autoProject) {
        autoProject.setProjecttype(0);
        autoProject.setProjectcreatedate(new Date());
        int count = autoProjectMapper.insert(autoProject);
        autoUserPro.setProjectid(autoProject.getId());
        autoUserPro.setUid(autoProject.getUid());
        int sum = autoUserProMapper.insert(autoUserPro);
        if (count != 1 || sum != 1) {
            return RespBean.error("检查参数");
        }
        return RespBean.ok("添加成功",autoProject);
    }

    @Override
    public RespBean deleteProject(Integer projectId) {
        AutoPage autoPage =  autoPageMapper.queryByProjectId(projectId);
        if (autoPage != null) {
            return RespBean.error("请先删除项目下的页面！！！");
        }
        AutoProject autoProject = queryById(projectId);
        int sum = autoUserProMapper.deleteByProjectId(projectId);
        int count = autoProjectMapper.deleteById(projectId);
        if (count != 1 || sum == 0) {
            return RespBean.error("检查参数");
        }
        return RespBean.ok("删除成功",autoProject);
    }

    @Override
    public RespBean addProjectUser(Integer projectId, Integer userId) {
        AutoUserPro autoUserPro = autoUserProMapper.selectObject(projectId,userId);
        if (autoUserPro != null) {
            return RespBean.error("已添加，不需要再次添加！！");
        }
        int sum = autoUserProMapper.addProjectUser(projectId,userId);
        if (sum != 1) {
            return RespBean.error("添加组员失败");
        }
        return RespBean.ok("添加成功");
    }

    @Override
    public RespBean updateProjectUser(AutoProject autoProject) {
        int count = autoProjectMapper.update(autoProject);
        if (count != 1) {
            return RespBean.error("修改失败！！！");
        }
        AutoProject autoProject1 = autoProjectMapper.queryById(autoProject.getId());
        return RespBean.ok("修改成功！！！",autoProject);
    }

    @Override
    public RespBean getUserProList(Integer userId) {
        List<AutoProject> autoProjectList = autoProjectMapper.selectAllProjectListByUser(userId);
        if (autoProjectList.size() == 0) {
            return RespBean.error("该用户没有测试项目！！！");
        }
        return RespBean.ok("获取成功！！！",autoProjectList);
    }

    @Override
    public RespBean getProjectNameList(Integer userId) {
        List<String> autoProjectList = autoProjectMapper.getProjectNameList(userId);
        return RespBean.ok("获取成功！！！",autoProjectList);
    }

    @Override
    public List<AutoProject> findUserProList(Integer userId) {
        return autoProjectMapper.findUserProList(userId);
    }
}
