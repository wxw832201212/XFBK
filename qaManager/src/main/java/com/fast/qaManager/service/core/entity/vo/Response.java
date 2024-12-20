package com.fast.qaManager.service.core.entity.vo;

import java.io.Serializable;
import java.util.Map;

/**
 * @Description
 * @Author jianghuadong
 * @Date 2022/1/26 14:07
 */
public class Response implements Serializable {
    private int code;
    private String msg;
    private Map<String,Object> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
