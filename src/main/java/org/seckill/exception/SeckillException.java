package org.seckill.exception;

/**
 * 秒杀相关的业务异常
 * Created by never615 on 6/16/16.
 */
public class SeckillException extends RuntimeException {

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
