package com.xiaoliu.common.enums;

/**
 * @description: 请求响应状态码
 * @author: FubiaoLiu
 * @date: 2018/11/30
 */
public enum StatusCodeEnum {
    /**
     * 请求成功
     */
    OK(200, "请求成功！"),
    /**
     * 请求失败
     */
    INVALID_REQUEST(400, "请求失败！"),
    /**
     * 未经授权的
     */
    UNAUTHORIZED(401, "未经授权的！"),
    /**
     * 无权限角色登录报402
     */
    UNSUPPORTED(402, "无权限！"),
    /**
     * 用户未登录
     */
    NO_LOGGED(403, "用户未登录！"),
    /**
     * 用户登录失效
     */
    LOGON_FAILURE(404, "用户登录失效！"),
    /**
     * 系统异常
     */
    INTERNAL_SERVER_ERROR(500, "系统异常，请联系管理员！"),
    /**
     * 参数异常
     */
    INVALID_PARAM_ERROR(501, "参数异常，请联系管理员！"),
    /**
     * 数据异常
     */
    DATA_ABNORMITY_ERROR(502, "数据异常，请联系管理员！"),
    /**
     * 用户不存在
     */
    USER_NOT_EXIST(503, "用户不存在！"),
    /**
     * 账号异常
     */
    ACCOUNT_EXCEPTION(504, "账号异常，请联系管理员！"),
    /**
     * 账号过期
     */
    ACCOUNT_EXPIRE(505, "账号过期，请联系管理员！"),
    /**
     * 用户名或密码错误
     */
    USERNAME_OR_PWD_WRONG(506, "用户名或密码错误！"),
    /**
     * 当前用户无对应角色
     */
    USERNAME_NO_ROLE(507, "当前用户无对应角色！"),
    /**
     * 无权限操作
     */
    UNSUPPORTED_OPERATE(508, "非当前账户创建，无权限操作！"),
    /**
     * 校验失败
     **/
    CHECK_FAILURE(509, "校验失败!"),
    /**
     * 操作失败
     **/
    OPERATION_FAILED(510, "操作失败!"),
    /**
     * 刷新token
     */
    REFRESH_TOKEN(666, "REFRESH_TOKEN！");

    private int key;
    private String value;

    StatusCodeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static String getValue(int key) {
        String result = "";
        for (StatusCodeEnum status : StatusCodeEnum.values()) {
            if (status.getKey() == key) {
                result = status.getValue();
            }
        }
        return result;
    }
}
