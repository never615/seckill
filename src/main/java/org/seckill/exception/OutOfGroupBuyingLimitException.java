package org.seckill.exception;

/**
 * 超出团购购买数量异常
 * Created by never615 on 6/23/16.
 */
public class OutOfGroupBuyingLimitException extends GroupBuyingException {


    public OutOfGroupBuyingLimitException(String message) {
        super(message);
    }

    public OutOfGroupBuyingLimitException(String message, Throwable cause) {
        super(message, cause);
    }
}
