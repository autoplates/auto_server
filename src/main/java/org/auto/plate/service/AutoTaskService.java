package org.auto.plate.service;

import org.auto.plate.entity.AutoTask;
import org.auto.plate.entity.RespBean;
import org.springframework.stereotype.Service;

@Service
public interface AutoTaskService {
    RespBean getAutoTaskList(String query, Integer userId, Integer pageNum, Integer pageSize);

    AutoTask getAutoTaskById(Integer taskId);

    RespBean saveTask(AutoTask autoTask);

    RespBean getAutoTaskList(Integer userId);
}
