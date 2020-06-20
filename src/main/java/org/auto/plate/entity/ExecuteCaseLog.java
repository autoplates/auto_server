package org.auto.plate.entity;

import java.io.Serializable;
import java.util.List;

public class ExecuteCaseLog implements Serializable {

    private List<String> ExecuteCaseLogList;

    public List<String> getExecuteCaseLogList() {
        return ExecuteCaseLogList;
    }

    public void setExcuteCaseLogList(List<String> executeCaseLogList) {
        ExecuteCaseLogList = executeCaseLogList;
    }
}
