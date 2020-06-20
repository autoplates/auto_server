package org.auto.plate.controller;

import org.auto.plate.entity.AutoTask;
import org.auto.plate.entity.RespBean;
import org.auto.plate.service.AutoTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * (AutoProject)表控制层
 *
 * @author makejava
 * @since 2020-04-13 01:51:18
 */
@RestController
@RequestMapping("api")
public class AutoTaskController {

    @Autowired
    AutoTaskService autoTaskService;

    @RequestMapping(value = "/getTaskList", method = RequestMethod.GET)
    public RespBean getAutoTaskList(@RequestParam String query, Integer userId, Integer pageNum, Integer pageSize) {
        return autoTaskService.getAutoTaskList(query, userId, pageNum, pageSize);
    }

    @RequestMapping(value = "/saveTask", method = RequestMethod.POST)
    public RespBean saveTask(@RequestBody AutoTask autoTask) {
        return autoTaskService.saveTask(autoTask);
    }

    @RequestMapping(value = "/getAllTaskList", method = RequestMethod.GET)
    public RespBean getAllTaskList(@RequestParam Integer userId) {
        return autoTaskService.getAutoTaskList(userId);
    }
}
