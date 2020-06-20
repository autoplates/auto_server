package org.auto.plate.entity;

import java.io.Serializable;

/**
 * (AutoUserPro)实体类
 *
 * @author makejava
 * @since 2020-04-15 20:59:35
 */
public class AutoUserPro implements Serializable {
    private static final long serialVersionUID = 926249698326710455L;
    
    private Integer id;
    
    private Integer uid;
    
    private Integer projectid;


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

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

}