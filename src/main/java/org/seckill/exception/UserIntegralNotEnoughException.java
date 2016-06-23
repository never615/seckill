package org.seckill.exception;

/**
 * 用户积分不足异常
 * Created by never615 on 6/23/16.
 */
public class UserIntegralNotEnoughException extends Exception {
    public UserIntegralNotEnoughException(String message) {
        super(message);
    }

    public UserIntegralNotEnoughException(String message, Throwable cause) {
        super(message, cause);
    }
}
