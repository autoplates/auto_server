package org.auto.plate.controller;

import org.auto.plate.entity.AutoCase;
import org.auto.plate.entity.RespBean;
import org.auto.plate.service.ExecuteService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (AutoCase)表控制层
 *
 * @author makejava
 * @since 2020-04-18 23:34:05
 */
@RestController
@RequestMapping("api")
public class ExecuteController {

    @Resource
    ExecuteService executService;

    /**
     * 执行用例
     * @param caseId
     * @param model
     * @param browserType
     * @return
     * @throws Exception
     */
    @GetMapping("/executeAutoCase")
    public RespBean executeAutoTestCase(@RequestParam Integer caseId, Integer model, String browserType) throws Exception {
        return executService.execute(caseId, model, browserType);
    }
    @RequestMapping(value = "/executeCase", method = RequestMethod.POST)
    public RespBean executeTest(@RequestBody AutoCase autoCase, @RequestParam String browserType) {
        return executService.executeCase(autoCase,browserType);
    }

    @GetMapping("/executeAutoTask")
    public RespBean executeTask(@RequestParam Integer taskId, Integer model, String browserType){
        return executService.executeTask(taskId, model, browserType);
    }
}
