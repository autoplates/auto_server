package org.auto.plate.utils.impl;

import java.util.HashMap;
import java.util.Map;

public class SeleAssert {

    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "FAIL";
    public static final String Error = "ERROR";

    private Map<String,Object> assertResultMap = new HashMap<String, Object>();

    public Map<String, Object> getAssertResultMap() {
        return assertResultMap;
    }

    /**
     * 字符串校验
     * @param object
     * @param param
     * @return
     */
    public Map verifyEquals(Object object, String param, String assertRule) {
        assertRule = "[==]";
        if (object instanceof String) {
            String result = "<font style='color: blue;'>" + object + "</font>" + assertRule + param;
            if (param.equals(object)){
                this.getAssertResultMap().put(SUCCESS, result);
            }else {
                this.getAssertResultMap().put(FAIL, result);
            }
            return this.getAssertResultMap();
        }
        this.getAssertResultMap().put(Error, "<font style='color: blue;'>" + object + "</font>" + assertRule + param);
        return this.getAssertResultMap();
    }
}
