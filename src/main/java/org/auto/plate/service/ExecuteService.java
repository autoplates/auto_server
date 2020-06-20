package org.auto.plate.service;

import org.auto.plate.entity.AutoCase;
import org.auto.plate.entity.RespBean;

public interface ExecuteService {
    RespBean execute(Integer caseId, Integer model, String browserType) throws Exception;

    RespBean executeCase(AutoCase autoCase, String browserType);

    RespBean executeTask(Integer taskId, Integer model, String browserType);
}
