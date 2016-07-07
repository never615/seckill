package org.seckill.dao;

import org.apache.commons.collections.MapUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配置Spring和Junit整合,junit启动时加载springIOC容器
 * spring-test,junit
 * Created by never615 on 6/16/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {
                "classpath:spring/spring-dao.xml",
                "classpath:spring/spring-service.xml"
        }
)
public class SeckillDaoTest {
    //注入Dao实现类依赖
    @Resource
    private SeckillDao seckillDao;

    @Resource
    private TransactionTemplate txTemplate;


    /**
     * 测试查询单个秒杀商品条目
     *
     * @throws Exception
     */
    @Test
    public void testQueryById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill);
    }


    /**
     * 测试查询全部秒杀商品
     *
     * @throws Exception
     */
    @Test
    public void testQueryAll() throws Exception {
        System.out.println("-----------------------------------");

//        Java没有保存形参的记录:QueryAll(int offset,int limit)->QueryAll(arg0,arg1);
//      因为java形参的问题,多个基本类型参数的时候需要用@Param("seckillId")注解区分开来
        List<Seckill> seckills = seckillDao.queryAll(0, 100);
        for (Seckill seckill : seckills) {
            System.out.println(seckill);
        }
    }


    /**
     * 测试查询全部秒杀商品
     *
     * @throws Exception
     */
    @Test
    public void testQueryAllbyMerchantId() throws Exception {
        System.out.println("-----------------------------------");

//        Java没有保存形参的记录:QueryAll(int offset,int limit)->QueryAll(arg0,arg1);
//      因为java形参的问题,多个基本类型参数的时候需要用@Param("seckillId")注解区分开来
        List<Seckill> seckills = seckillDao.queryAllbyMerchant(1,0, 100);
        for (Seckill seckill : seckills) {
            System.out.println(seckill);
        }
    }

//    /**
//     * 测试减少秒杀商品数量
//     *
//     * @throws Exception
//     */
//    @TestToekn
//    public void testReduceNumber() throws Exception {
//        Date killTime = new Date();
//        int updateCount = seckillDao.reduceNumber(1001L, killTime);
//        System.out.println("updateCount:  " + updateCount);
//    }


    /**
     * 测试存储过程
     * 秒杀:插入秒杀记录,更新秒杀商品数量
     */
    @Test
    public void killByProcedure() {
        System.out.println("-----------------------------------");

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);


        PlatformTransactionManager transactionManager = txTemplate.getTransactionManager();

        TransactionStatus status = transactionManager.getTransaction(def);



        //生成核销码
//        String verificationCode=

        Date killTime = new Date();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("seckillId", 1001);
//        map.put("userId", 674);
        map.put("userId", 713);
        map.put("seckillAt", killTime);
        map.put("verificationCode","987897896597687");
        map.put("result", -201);




        try {
            seckillDao.killByProcedure(map);

        } catch (DuplicateKeyException e) {
            System.out.println("重复秒杀异常:" + e);
            transactionManager.rollback(status);
            return;
        } catch (Exception e) {
            System.out.println("sql异常:" + e);
            transactionManager.rollback(status);
            return;
        }


        int result = MapUtils.getInteger(map, "result", -200);

        if (result != 0) {
            transactionManager.rollback(status);
        } else {
            transactionManager.commit(status);
        }


        System.out.println("秒杀结果:" + result);
    }
}
