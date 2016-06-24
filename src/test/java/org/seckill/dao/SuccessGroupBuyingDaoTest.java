package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessGroupBuying;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by never615 on 6/23/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {
                "classpath:spring/spring-dao.xml",
                "classpath:spring/spring-service.xml"
        }
)
public class SuccessGroupBuyingDaoTest {

    @Resource GroupBuyingDao groupBuyingDao;
    @Resource SuccessGroupBuyingDao successGroupBuyingDao;

    @Test
    public void testInsertSuccessGroupBuying() throws Exception {

        Date nowTime =new Date();
        long groupBuyingId=1000;
        long userId=713;

        long successGroupBuyingId = successGroupBuyingDao.insertSuccessGroupBuying(groupBuyingId, userId, nowTime);
        System.out.println("successGroupBuyingId:"+successGroupBuyingId);
    }

    @Test
    public void testQueryByIdWithGroupBuying() throws Exception {
        long successGroupBuyingId=6;
        SuccessGroupBuying successGroupBuying = successGroupBuyingDao.queryByIdWithGroupBuying(successGroupBuyingId);
        System.out.println(successGroupBuying);
    }

    @Test
    public void testQueryByUserIdAndGroupBuyingId() throws Exception {
        long userId=713;
        long groupBuyingId=1000;
        long count = successGroupBuyingDao.queryByUserIdAndGroupBuyingId(userId, groupBuyingId);
        System.out.println("count:"+count);
    }

    @Test
    public void testQueryByUserIdWithGroupBuying() throws Exception {
        long userId=713;
        List<SuccessGroupBuying> successGroupBuyings = successGroupBuyingDao.queryByUserIdWithGroupBuying(userId);
        for (SuccessGroupBuying successGroupBuying : successGroupBuyings) {
            System.out.println(successGroupBuying);
        }

    }

    @Test
    public void testUpdateVerificationCode() throws Exception {
        int i = successGroupBuyingDao.updateVerificationCode(6, "3000000020130210");
        System.out.println("count:"+i);
    }
}