package org.auto.plate.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (AutoCase)实体类
 *
 * @author makejava
 * @since 2020-04-18 23:34:04
 */
public class AutoCase implements Serializable {
    private static final long serialVersionUID = 757104767874006335L;
    
    private Integer id;
    
    private String casename;
    
    private String casedescription;
    
    private Date createtime;
    
    private Date updatetime;
    
    private Date executetime;
    
    private Integer status;
    
    private Integer proid;
    
    private String proname;
    
    private String elelist;
    
    private String paramslist;

    private String pretemplate;

    private String posttemplate;

    private String executelog;

    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPretemplate() {
        return pretemplate;
    }

    public void setPretemplate(String pretemplate) {
        this.pretemplate = pretemplate;
    }

    public String getPosttemplate() {
        return posttemplate;
    }

    public void setPosttemplate(String posttemplate) {
        this.posttemplate = posttemplate;
    }

    public String getExecutelog() {
        return executelog;
    }

    public void setExecutelog(String executelog) {
        this.executelog = executelog;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCasename() {
        return casename;
    }

    public void setCasename(String casename) {
        this.casename = casename;
    }

    public String getCasedescription() {
        return casedescription;
    }

    public void setCasedescription(String casedescription) {
        this.casedescription = casedescription;
    }

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd hh:mm:ss")
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd hh:mm:ss")
    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd hh:mm:ss")
    public Date getExecutetime() {
        return executetime;
    }

    public void setExecutetime(Date executetime) {
        this.executetime = executetime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getProid() {
        return proid;
    }

    public void setProid(Integer proid) {
        this.proid = proid;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public String getElelist() {
        return elelist;
    }

    public void setElelist(String elelist) {
        this.elelist = elelist;
    }

    public String getParamslist() {
        return paramslist;
    }

    public void setParamslist(String paramslist) {
        this.paramslist = paramslist;
    }
}