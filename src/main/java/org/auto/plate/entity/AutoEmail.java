package org.auto.plate.entity;

public class AutoEmail {

    private Integer id;

    private String sendto;

    private String copyto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSendto() {
        return sendto;
    }

    public void setSendto(String sendto) {
        this.sendto = sendto;
    }

    public String getCopyto() {
        return copyto;
    }

    public void setCopyto(String copyto) {
        this.copyto = copyto;
    }
}
