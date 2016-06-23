package org.seckill.enums;

/**
 * 接口请求状态码及描述
 * <p>
 * 使用枚举 表示 常量的数据字段
 * Created by never615 on 6/23/16.
 */
public enum RequestStateEnum {
    SUCCESS(0, "请求成功"),
    USER_INEXISTENCE(1000, "用户不存在"),
    TOKEN_EXCEPTION(1007, "token不可用"),
    INNER_ERROR(500, "系统内部异常");

    private int state;

    private String stateInfo;

    RequestStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static RequestStateEnum stateOf(int index) {
        for (RequestStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }

}
