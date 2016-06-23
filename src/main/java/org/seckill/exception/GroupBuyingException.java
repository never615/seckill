package org.seckill.exception;

/**
 * 团购异常
 * Created by never615 on 6/23/16.
 */
public class GroupBuyingException extends Exception {
    public GroupBuyingException(String message) {
        super(message);
    }

    public GroupBuyingException(String message, Throwable cause) {
        super(message, cause);
    }
}
