package org.auto.plate.entity;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AutoCaseListObj {

    private List<AutoCase> autoCaseList;

    private Integer total;

    public List<AutoCase> getAutoCaseList() {
        return autoCaseList;
    }

    public void setAutoCaseList(List<AutoCase> autoCaseList) {
        this.autoCaseList = autoCaseList;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
