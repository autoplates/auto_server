package org.auto.plate.entity;

import java.io.Serializable;

public class AutoEleAddress implements Serializable {

    private static final long serialVersionUID = -42156730906806700L;

    private Integer id;
    private String addressType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }
}
