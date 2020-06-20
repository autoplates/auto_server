package org.auto.plate.entity;

public class RespBean {
    private Integer status;
    private String msg;
    private Object obj;
    private String log;

    public static RespBean ok(String msg, Object obj) {
        return new RespBean(200, msg, obj);
    }

    public static RespBean ok(String msg) {
        return new RespBean(200, msg, null);
    }

    public static RespBean error(String msg, Object obj) {
        return new RespBean(500, msg, obj);
    }

    public static RespBean error(String msg) {
        return new RespBean(500, msg, null);
    }

    public static RespBean ok(String msg, String log, Object obj) {
        return new RespBean(200, msg, log, obj);
    }

    public static RespBean error(String msg, String log, Object obj) {
        return new RespBean(500, msg, log,null);
    }

    private RespBean() {

    }

    /**
     * 全部成员变量的构造函数
     * @param status
     * @param msg
     * @param obj
     */
    private RespBean(Integer status, String msg, String log, Object obj) {
        this.status = status;
        this.msg = msg;
        this.log = log;
        this.obj = obj;
    }

    private RespBean(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
