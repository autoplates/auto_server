package org.auto.plate.entity;


import java.io.Serializable;

/**
 * (AutoPage)实体类
 *
 * @author makejava
 * @since 2020-04-16 22:09:55
 */
public class AutoEventType implements Serializable {

    private static final long serialVersionUID = -42156730906806700L;

    private Integer id;
    private String eventType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
