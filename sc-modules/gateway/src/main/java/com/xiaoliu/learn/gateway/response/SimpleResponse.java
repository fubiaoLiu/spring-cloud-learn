package com.xiaoliu.learn.gateway.response;

import com.xiaoliu.common.enums.StatusCodeEnum;
import lombok.Data;

/**
 * @description: 返回数据包装类
 * @author: FuBiaoLiu
 * @date: 2018/11/27
 */
@Data
public class SimpleResponse {

    private Integer statusCode;

    private String message;

    private String accessToken;
    /**
     * 返回内容
     */
    private Object data;

    public SimpleResponse() {
    }

    public SimpleResponse(Object data) {
        this.data = data;
    }


    public SimpleResponse(Object data, String accessToken) {
        this.data = data;
        this.setStatusCode(StatusCodeEnum.REFRESH_TOKEN.getKey());
        this.setMessage(StatusCodeEnum.REFRESH_TOKEN.getValue());
        this.accessToken = accessToken;
    }

    public static SimpleResponse resultAccessToken(Object data, String accessToken) {
        SimpleResponse response = new SimpleResponse();
        response.setData(data);
        response.setStatusCode(StatusCodeEnum.REFRESH_TOKEN.getKey());
        response.setMessage(StatusCodeEnum.REFRESH_TOKEN.getValue());
        response.setAccessToken(accessToken);
        return response;
    }

}
