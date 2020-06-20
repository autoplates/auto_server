package org.auto.plate.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.io.Serializable;

/**
 * (AutoProject)实体类
 *
 * @author makejava
 * @since 2020-04-13 02:02:23
 */
public class AutoProject implements Serializable {
    private static final long serialVersionUID = -73700097633589750L;
    
    private Integer id;
    
    private Integer uid;
    
    private String projectname;
    
    private Integer projecttype;
    
    private String projectdescription;
    
    private String projectaddress;
    
    private Date projectcreatedate;
    
    private String projectcreateuser;
    
    private Date projectupdatedate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public Integer getProjecttype() {
        return projecttype;
    }

    public void setProjecttype(Integer projecttype) {
        this.projecttype = projecttype;
    }

    public String getProjectdescription() {
        return projectdescription;
    }

    public void setProjectdescription(String projectdescription) {
        this.projectdescription = projectdescription;
    }

    public String getProjectaddress() {
        return projectaddress;
    }

    public void setProjectaddress(String projectaddress) {
        this.projectaddress = projectaddress;
    }

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd hh:mm:ss")
    public Date getProjectcreatedate() {
        return projectcreatedate;
    }

    public void setProjectcreatedate(Date projectcreatedate) {
        this.projectcreatedate = projectcreatedate;
    }

    public String getProjectcreateuser() {
        return projectcreateuser;
    }

    public void setProjectcreateuser(String projectcreateuser) {
        this.projectcreateuser = projectcreateuser;
    }

    public Date getProjectupdatedate() {
        return projectupdatedate;
    }

    public void setProjectupdatedate(Date projectupdatedate) {
        this.projectupdatedate = projectupdatedate;
    }

}