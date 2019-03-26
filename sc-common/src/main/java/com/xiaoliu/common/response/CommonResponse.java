package com.xiaoliu.common.response;

import com.xiaoliu.common.enums.StatusCodeEnum;
import com.thoughtworks.xstream.core.BaseException;

/**
 * @description: 响应格式
 * @author: FubiaoLiu
 * @date: 2018/12/1
 */
public class CommonResponse {

    /**
     * 返回状态
     *
     * @see StatusCodeEnum
     */
    private Integer statusCode;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返给前端的字段标识
     */
    private String field;

    /**
     * 错误返回结果msg
     */
    public static final String RESULT_MSG_FAILURE = "操作失败！";

    /**
     * 正确返回结果msg
     */
    public static final String RESULT_MSG_SUCCESS = "操作成功！";

    /**
     * 返回内容
     */
    private Object data;


    public CommonResponse() {
        this.statusCode = StatusCodeEnum.OK.getKey();
    }

    public CommonResponse(Object data) {
        this.statusCode = StatusCodeEnum.OK.getKey();
        this.data = data;
    }

    public CommonResponse(StatusCodeEnum statusCode) {
        this.statusCode = statusCode.getKey();
        this.message = statusCode.getValue();
    }

    public CommonResponse(Object data, StatusCodeEnum statusCode) {
        this.data = data;
        this.statusCode = statusCode.getKey();
        this.message = statusCode.getValue();
    }

    public CommonResponse(StatusCodeEnum statusCode, String message) {
        if (statusCode != null) {
            this.statusCode = statusCode.getKey();
        }
        this.message = message;
    }

    public CommonResponse(Object data, StatusCodeEnum statusCode, String message) {
        this.data = data;
        if (statusCode != null) {
            this.statusCode = statusCode.getKey();
        }
        this.message = message;
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

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    /**
     * 功能说明：返回一个请求成功的响应模板
     *
     * @param
     * @return CommonResponse
     */
    public static CommonResponse success() {
        CommonResponse response = new CommonResponse();
        response.setStatusCode(StatusCodeEnum.OK.getKey());
        response.setMessage(RESULT_MSG_SUCCESS);
        return response;
    }

    /**
     * 功能说明：返回一个请求成功的响应模板
     *
     * @param data
     * @return CommonResponse
     */
    public static CommonResponse success(Object data) {
        CommonResponse response = new CommonResponse(data);
        response.setStatusCode(StatusCodeEnum.OK.getKey());
        response.setMessage(RESULT_MSG_SUCCESS);
        return response;
    }

    /**
     * 功能说明：返回一个请求失败的响应模板
     *
     * @return CommonResponse 错误类别为默认的INVALID_REQUEST（请求错误）
     */
    public static CommonResponse failure() {
        CommonResponse response = new CommonResponse();
        response.setStatusCode(StatusCodeEnum.INVALID_REQUEST.getKey());
        response.setMessage(RESULT_MSG_FAILURE);
        return response;
    }

    /**
     * 功能说明：返回一个请求失败的响应模板
     *
     * @param message
     * @return CommonResponse 错误类别为默认的INVALID_REQUEST（请求错误）
     */
    public static CommonResponse failure(String message) {
        CommonResponse response = new CommonResponse();
        response.setMessage(message);
        response.setStatusCode(StatusCodeEnum.INVALID_REQUEST.getKey());
        return response;
    }

    /**
     * 功能说明：返回一个请求失败的响应模板，具体错误类型根据参数决定
     *
     * @param statusCode 错误类型
     * @return CommonResponse
     */
    public static CommonResponse failure(StatusCodeEnum statusCode) {
        CommonResponse response = new CommonResponse();
        response.setStatusCode(statusCode.getKey());
        response.setMessage(statusCode.getValue());
        return response;
    }

    /**
     * 功能说明：返回一个请求失败的响应模板，具体错误类型根据参数决定
     *
     * @param statusCode
     * @return CommonResponse 错误类别为默认的INVALID_REQUEST（请求错误）
     */
    public static CommonResponse failure(StatusCodeEnum statusCode, String message) {
        CommonResponse response = new CommonResponse();
        response.setStatusCode(statusCode.getKey());
        response.setMessage(message);
        return response;
    }


    /**
     * 功能说明：返回一个请求失败的响应模板
     *
     * @param e 异常参数不能为空
     * @return CommonResponse
     */
    public static CommonResponse failure(Exception e) {
        CommonResponse response = new CommonResponse();
        response.setMessage(RESULT_MSG_FAILURE);
        response.setStatusCode(StatusCodeEnum.INTERNAL_SERVER_ERROR.getKey());
        return response;
    }

    /**
     * 功能说明：返回一个请求失败的响应模板
     *
     * @param e 异常参数不能为空
     * @return CommonResponse
     */
    public static CommonResponse failure(BaseException e) {
        CommonResponse response = new CommonResponse();
        if (e != null) {
            response.setStatusCode(getStatusCode(e));
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * 功能说明：返回一个请求失败未经授权的响应模板
     *
     * @return CommonResponse 错误类别为默认的UNAUTHORIZED（未经授权）
     */
    public static CommonResponse unauthorized() {
        CommonResponse response = new CommonResponse();
        response.setStatusCode(StatusCodeEnum.UNAUTHORIZED.getKey());
        response.setMessage(StatusCodeEnum.UNAUTHORIZED.getValue());
        return response;
    }

    /**
     * 功能说明：返回一个请求失败未经授权的响应模板
     *
     * @param message
     * @return CommonResponse 错误类别为默认的UNAUTHORIZED（未经授权）
     */
    public static CommonResponse unauthorized(String message) {
        CommonResponse response = new CommonResponse();
        response.setStatusCode(StatusCodeEnum.UNAUTHORIZED.getKey());
        response.setMessage(message);
        return response;
    }

    /**
     * 功能说明：返回一个校验的响应模板,带上了字段标识方便前端定位输入框
     *
     * @param message 错误提示信息
     * @return CommonResponse 错误类别为默认为校验失败
     */
    public static CommonResponse checkFailure(String field, String message) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setMessage(message);
        commonResponse.setField(field);
        commonResponse.setStatusCode(StatusCodeEnum.CHECK_FAILURE.getKey());
        return commonResponse;
    }

    /**
     * 功能说明：返回一个校验的响应模板
     *
     * @param message 错误提示信息
     * @return CommonResponse 错误类别为默认为校验失败
     */
    public static CommonResponse checkFailure(String message) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setMessage(message);
        commonResponse.setStatusCode(StatusCodeEnum.CHECK_FAILURE.getKey());
        return commonResponse;
    }
    /**
     * 功能说明：返回一个校验的响应模板
     *
     * @param message 错误提示信息
     * @param data 返回数据
     * @return CommonResponse 错误类别为默认为校验失败
     */
    public static CommonResponse checkFailure(String message,Object data) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setMessage(message);
        commonResponse.setData(data);
        commonResponse.setStatusCode(StatusCodeEnum.CHECK_FAILURE.getKey());
        return commonResponse;
    }

    /**
     * 功能说明：返回一个操作失败的响应模板,带上了字段标识方便前端定位输入框
     *
     * @param message 错误提示信息
     * @return CommonResponse 错误类别为默认为操作失败
     */
    public static CommonResponse operationFailed(String field, String message) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setMessage(message);
        commonResponse.setField(field);
        commonResponse.setStatusCode(StatusCodeEnum.OPERATION_FAILED.getKey());
        return commonResponse;
    }

    /**
     * 功能说明：返回一个操作失败的响应模板
     *
     * @param message 错误提示信息
     * @return CommonResponse 错误类别为默认为操作失败
     */
    public static CommonResponse operationFailed(String message) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setMessage(message);
        commonResponse.setStatusCode(StatusCodeEnum.OPERATION_FAILED.getKey());
        return commonResponse;
    }


    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 功能说明：根据错误类别返回错误码
     *
     * @param e
     * @return Integer
     */
    public static Integer getStatusCode(BaseException e) {
        return StatusCodeEnum.INTERNAL_SERVER_ERROR.getKey();
    }

    @Override
    public String toString() {
        return "CommonResponse{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public String toJsonString() {
        return "{" +
                "\"statusCode\":\"" + statusCode +
                "\", \"message\":\"" + message +
                "\", \"data\":\"" + data +
                "\"}";
    }
}
