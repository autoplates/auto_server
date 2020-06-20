package org.auto.plate.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (AutoElement)实体类
 *
 * @author makejava
 * @since 2020-04-18 17:07:05
 */
public class AutoElement implements Serializable {
    private static final long serialVersionUID = -42156730906806700L;
    
    private Integer id;
    
    private String elename;
    
    private String eleeventname;
    
    private String elelocaltype;
    
    private String eleaddress;
    
    private Date createdate;
    
    private Date updatedate;
    
    private Integer pageid;
    
    private String createby;
    
    private String lastupdateby;
    
    private String pagename;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getElename() {
        return elename;
    }

    public void setElename(String elename) {
        this.elename = elename;
    }

    public String getEleeventname() {
        return eleeventname;
    }

    public void setEleeventname(String eleeventname) {
        this.eleeventname = eleeventname;
    }

    public String getElelocaltype() {
        return elelocaltype;
    }

    public void setElelocaltype(String elelocaltype) {
        this.elelocaltype = elelocaltype;
    }

    public String getEleaddress() {
        return eleaddress;
    }

    public void setEleaddress(String eleaddress) {
        this.eleaddress = eleaddress;
    }
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd hh:mm:ss")
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public Integer getPageid() {
        return pageid;
    }

    public void setPageid(Integer pageid) {
        this.pageid = pageid;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public String getLastupdateby() {
        return lastupdateby;
    }

    public void setLastupdateby(String lastupdateby) {
        this.lastupdateby = lastupdateby;
    }

    public String getPagename() {
        return pagename;
    }

    public void setPagename(String pagename) {
        this.pagename = pagename;
    }

}