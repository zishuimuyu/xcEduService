package com.xuecheng.framework.model.response;

import lombok.ToString;

/**
 * @author：GJH
 * @createDate：2019/9/28
 * @company：洪荒宇宙加力蹲大学
 */
@ToString
public enum
CommonCode implements ResultCode {
    /**
     * 操作成功
     */
    SUCCESS(true, 10000, "操作成功！"),
    /**
     * 操作失败
     */
    FAIL(false, 11111, "操作失败！"),
    /**
     * 此操作需要登陆系统
     */
    UNAUTHENTICATED(false, 10001, "此操作需要登陆系统！"),

    /* 权限错误：70001-79999 */
    /**
     * 权限不足，无权操作
     */
    UNAUTHORISE(false, 10002, "权限不足，无权操作！"),
    /**
     * 登录信息已过期
     */
    PERMISSION_LOST(false, 70002, "登录信息已过期"),


    //系统错误：40001-49999
    /**
     * 抱歉，系统繁忙，请稍后重试！
     */
    SERVER_ERROR(false, 99999, "抱歉，系统繁忙，请稍后重试！"),

    /* 参数错误：10001-19999 */
    /**
     * 参数非法
     */
    INVALID_PARAM(false, 10003, "参数非法！"),
    PARAM_NOT_COMPLETE(false, 10004, "参数缺失"),
    PARAM_TYPE_BIND_ERROR(false, 10005, "参数类型错误"),

    /* 用户错误：20001-29999*/
    USER_NOT_LOGGED_IN(false, 20001, "用户未登录"),
    USER_LOGIN_ERROR(false, 20002, "密码错误"),
    USER_ACCOUNT_FORBIDDEN(false, 20003, "账号已被冻结"),
    USER_NOT_EXIST(false, 20004, "用户不存在"),
    USER_HAS_EXISTED(false, 20005, "用户已存在"),
    USER_SIGN_ERROR(false, 20006, "用户登录异常"),
    USER_OLD_PWD_ERROR(false, 20007, "旧密码错误"),
    USER_HAS_NOT_ROLE(false, 20007, "用户未配置角色"),

    /* 业务错误：30001-39999 */
    SPECIFIED_QUESTIONED_USER_NOT_EXIST(false, 30001, "某业务出现问题"),


    /* 数据错误：50001-599999 */
    RESULE_DATA_NONE(false, 50001, "数据未找到"),
    DATA_IS_WRONG(false, 50002, "数据有误"),
    DATA_ALREADY_EXISTED(false, 50003, "数据已存在"),

    /* 接口错误：60001-69999 */
    INTERFACE_INNER_INVOKE_ERROR(false, 60001, "内部系统接口调用异常"),
    INTERFACE_OUTTER_INVOKE_ERROR(false, 60002, "外部系统接口调用异常"),
    INTERFACE_FORBID_VISIT(false, 60003, "该接口禁止访问"),
    INTERFACE_ADDRESS_INVALID(false, 60004, "接口地址无效"),
    INTERFACE_REQUEST_TIMEOUT(false, 60005, "接口请求超时"),
    INTERFACE_EXCEED_LOAD(false, 60006, "接口负载过高"),


    /* 文件错误：80001-89999 */
    FILE_NOT_FOUND(false, 80001, "文件未找到"),
    FILE_ERROR(false, 80001, "文件错误"),

    /* 工作流错误：90000-99999 */
    FLOW_NOT_REPEAT_START(false, 90001, "不能重复启动"),
    FLOW_OTHER_HANDLED(false, 90002, "该业务已由其他人处理"),
    FLOW_EDIT_ERROR(false, 90003, "流程已经启动，不能更改和删除"),
    FLOW_FIRST_ACT(false, 90004, "此业务处于第一个节点，不能驳回");

    /**
     * 操作是否成功
     */
    boolean success;
    /**
     * 操作代码
     */
    int code;
    /**
     * 提示信息
     */
    String message;

    private CommonCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }


}
