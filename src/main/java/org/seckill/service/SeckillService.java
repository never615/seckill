package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillDto;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;

import java.util.List;


/**
 * 业务接口:站在"使用者"的角度设计接口
 * 1.方法的定义的粒度.2.参数.3.返回类型(return /异常)
 * Created by never615 on 6/16/16.
 */
public interface SeckillService {


    /**
     * 查询秒杀列表
     *
     * @param offset 偏移量
     * @param limit  请求的数量
     * @return
     */
    List<SeckillDto> getSeckillList(long offset, long limit);


    /**
     * 查询单个秒杀商品
     *
     * @param seckillId 秒杀id
     * @return 秒杀对象
     */
    SeckillDto getById(long seckillId);


    /**
     * 根据秒杀id查询秒杀地址
     *
     * @param seckillId 秒杀id
     * @return 秒杀暴露对象 : 秒杀开启时输出秒杀接口地址, 否则输出系统时间和秒杀时间
     */
    Exposer exportSeckillUrl(long seckillId);


//    /**
//     * 执行秒杀操作
//     *
//     * @param seckillId 秒杀id
//     * @param userId  用户id
//     * @param md5  md5校验码
//     * @return 秒杀结果
//     */
//    SeckillExecution executeSeckill(long seckillId, long userId, String md5) throws
//             RepeatKillException, SeckillCloseException,SeckillException;

    /**
     * 存储过程执行秒杀
     *
     * @param seckillId 秒杀id
     * @param userId    用户id
     * @param md5       校验码
     * @return SeckillExecution  秒杀结果
     */
    SeckillExecution executeSeckillProcedure(long seckillId, long userId, String md5);
}
