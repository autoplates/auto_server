package org.auto.plate.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (AutoPage)实体类
 *
 * @author makejava
 * @since 2020-04-16 22:09:55
 */
public class AutoPage implements Serializable {
    private static final long serialVersionUID = 760875412928238424L;
    
    private Integer id;
    
    private String pagename;
    
    private String pagedesc;
    
    private Integer elecount;
    
    private String createby;
    
    private Date createdate;
    
    private Integer projectid;
    
    private String projectname;

    private Integer userId;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPagename() {
        return pagename;
    }

    public void setPagename(String pagename) {
        this.pagename = pagename;
    }

    public String getPagedesc() {
        return pagedesc;
    }

    public void setPagedesc(String pagedesc) {
        this.pagedesc = pagedesc;
    }

    public Integer getElecount() {
        return elecount;
    }

    public void setElecount(Integer elecount) {
        this.elecount = elecount;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd hh:mm:ss")
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

}