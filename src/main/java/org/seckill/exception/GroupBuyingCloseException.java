package org.seckill.exception;

/**
 * 团购关闭异常
 * Created by never615 on 6/23/16.
 */
public class GroupBuyingCloseException extends GroupBuyingException {
    public GroupBuyingCloseException(String message) {
        super(message);
    }

    public GroupBuyingCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
