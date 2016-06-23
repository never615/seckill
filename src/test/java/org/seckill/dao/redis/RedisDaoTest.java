package org.seckill.dao.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SeckillDao;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {
                "classpath:spring/spring-dao.xml",
                "classpath:spring/spring-service.xml"
        }
)
public class RedisDaoTest {

    @Resource
    private RedisDao redisDao;

    @Resource
    private SeckillDao seckillDao;

    @Test
    public void testSeckill() throws Exception {
        System.out.println("--------------------");
        long seckillId = 1000L;
        Seckill seckill = null;
        seckill = redisDao.getSeckill(seckillId);
        if (seckill == null) {
            seckill = seckillDao.queryById(seckillId);
            if (seckill != null) {
                String result = redisDao.putSeckill(seckill);
                System.out.println(result);
            }
            seckill = redisDao.getSeckill(seckillId);
            System.out.println(seckill);
        }
    }
}
