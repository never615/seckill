package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;

import java.util.List;


/**
 * 业务接口:站在"使用者"的角度设计接口
 * 1.方法的定义的粒度.2.参数.3.返回类型(return /异常)
 * Created by never615 on 6/16/16.
 */
public interface UserService {

    /**
     * 根据用户id判断用户是否存在
     * @param userId 
     * @return
     */
    boolean isUserExist(long userId);

}
