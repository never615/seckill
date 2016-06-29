package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {
                "classpath:spring/spring-dao.xml"
        }
)
public class SuccessKilledDaoTest {

    @Resource
    private SuccessKilledDao successKilledDao;

//    /**
//     * 测试插入秒杀明细表
//     * @throws Exception
//     */
//    @TestToekn
//    public void testInsertSuccessKilled() throws Exception {
//        long id = 1000L;
//        long userId=674;
//        Date date=new Date();
//        int insertCount = successKilledDao.insertSuccessKilled(id, userId,date);
//        System.out.println("insertCount: " + insertCount);
//    }

    /**
     * 测试查询秒杀明细
     * @throws Exception
     */
    @Test
    public void testQueryByIdWithSeckill() throws Exception {

        long id = 1002L;
        long userId=674;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(id, userId);
        System.out.println(successKilled);
//        System.out.println(successKilled.getSeckill());

    }
}
