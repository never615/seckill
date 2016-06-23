package org.seckill.exception;

/**
 * 秒杀关闭
 * Created by never615 on 6/16/16.
 */
public class SeckillCloseException extends SeckillException {

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }

}
