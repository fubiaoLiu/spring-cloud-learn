package com.xiaoliu.learn.gateway.response;

import com.xiaoliu.common.enums.StatusCodeEnum;

/**
 * @author leyangjie
 * @date 2018/11/29 10:42
 * 返回数据包装类
 */
public class SimpleResponse {

    private Integer statusCode;

    private String message;

    private String access_token;
    /**
     * 返回内容
     */
    private Object data;

    public SimpleResponse() {
    }

    public SimpleResponse(Object data) {
        this.data = data;
    }


    public SimpleResponse(Object data, String access_token) {
        this.data = data;
        this.setStatusCode(StatusCodeEnum.REFRESH_TOKEN.getKey());
        this.setMessage(StatusCodeEnum.REFRESH_TOKEN.getValue());
        this.access_token = access_token;
    }

    public static SimpleResponse resultAccessToken(Object data, String access_token) {
        SimpleResponse response = new SimpleResponse();
        response.setData(data);
        response.setStatusCode(StatusCodeEnum.REFRESH_TOKEN.getKey());
        response.setMessage(StatusCodeEnum.REFRESH_TOKEN.getValue());
        response.setAccess_token(access_token);
        return response;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }


}
