package org.auto.plate.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (AutoCase)实体类
 *
 * @author makejava
 * @since 2020-04-18 23:34:04
 */
public class AutoTask implements Serializable {
    private static final long serialVersionUID = 757104767874006335L;

    private Integer id;

    private String caseList;

    private String taskname;

    private String taskdescription;

    private Date taskcreatetime;

    private Date taskupdatetime;

    private Integer status;

    private Date executetime;

    private String proname;

    private Integer proid;

    private Integer isScheduled;

    private Integer count;

    private Integer success;

    private Integer fail;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public Integer getProid() {
        return proid;
    }

    public void setProid(Integer proid) {
        this.proid = proid;
    }

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd hh:mm:ss")
    public Date getExecutetime() {
        return executetime;
    }

    public void setExecutetime(Date executetime) {
        this.executetime = executetime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCaseList() {
        return caseList;
    }

    public void setCaseList(String caseList) {
        this.caseList = caseList;
    }

    public String getTaskdescription() {
        return taskdescription;
    }

    public void setTaskdescription(String taskdescription) {
        this.taskdescription = taskdescription;
    }

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd hh:mm:ss")
    public Date getTaskcreatetime() {
        return taskcreatetime;
    }

    public void setTaskcreatetime(Date taskcreatetime) {
        this.taskcreatetime = taskcreatetime;
    }

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd hh:mm:ss")
    public Date getTaskupdatetime() {
        return taskupdatetime;
    }

    public void setTaskupdatetime(Date taskupdatetime) {
        this.taskupdatetime = taskupdatetime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public Integer getIsScheduled() {
        return isScheduled;
    }

    public void setIsScheduled(Integer isScheduled) {
        this.isScheduled = isScheduled;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Integer getFail() {
        return fail;
    }

    public void setFail(Integer fail) {
        this.fail = fail;
    }
}
