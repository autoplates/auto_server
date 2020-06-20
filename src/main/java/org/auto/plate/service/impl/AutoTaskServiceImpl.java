package org.auto.plate.service.impl;

import org.auto.plate.entity.AutoTask;
import org.auto.plate.entity.RespBean;
import org.auto.plate.mapper.AutoProjectMapper;
import org.auto.plate.mapper.AutoTaskMapper;
import org.auto.plate.service.AutoTaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AutoTaskServiceImpl implements AutoTaskService {

    @Resource
    AutoTaskMapper autoTaskMapper;
    @Resource
    AutoProjectMapper autoProjectMapper;

    @Override
    public RespBean getAutoTaskList(String query, Integer userId, Integer pageNum, Integer pageSize) {
        int firstNum = (pageNum-1) * pageSize;
        List<AutoTask> autoTaskList = autoTaskMapper.selectAllTaskList(query,userId,firstNum,pageSize);
        int total = autoTaskMapper.getAllTaskCount(userId);
        for (int i = 0; i < autoTaskList.size(); i++) {
            String[] autoTask_case = autoTaskList.get(i).getCaseList().split(",");
            autoTaskList.get(i).setCount(autoTask_case.length);
        }
        if (autoTaskList.size() == 0) {
            return RespBean.ok("暂无数据！！！");
        }
        return RespBean.ok("获取成功！！！",String.valueOf(total), autoTaskList);
    }

    @Override
    public AutoTask getAutoTaskById(Integer taskId) {
        return autoTaskMapper.selectTaskById(taskId);
    }

    @Override
    public RespBean saveTask(AutoTask autoTask) {
        autoTask.setStatus(1);
        autoTask.setTaskcreatetime(new Date());
        autoTask.setIsScheduled(0);
        autoTask.setFail(0);
        autoTask.setSuccess(0);
        autoTask.setProname(autoProjectMapper.queryById(autoTask.getProid()).getProjectname());
        int taskNum = autoTaskMapper.insert(autoTask);
        if (taskNum == 1) {
            return RespBean.ok("添加成功！！！",autoTask);
        }
        return RespBean.error("添加失败！！！","参数错误！！！",autoTask);
    }

    @Override
    public RespBean getAutoTaskList(Integer userId) {
        List<AutoTask> autoTaskList = autoTaskMapper.findTaskList(userId);
        for (int i = 0; i < autoTaskList.size(); i++) {
            String[] autoTask_case = autoTaskList.get(i).getCaseList().split(",");
            autoTaskList.get(i).setCount(autoTask_case.length);
        }
        return RespBean.ok("获取成功！！！",autoTaskList);
    }
}
