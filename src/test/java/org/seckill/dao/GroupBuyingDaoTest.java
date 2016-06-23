package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.GroupBuying;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

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
public class GroupBuyingDaoTest {

    @Resource GroupBuyingDao groupBuyingDao;

    @Test
    public void testReduceNumber() throws Exception {

        Date nowTime = new Date();
        long groupBuyingId = 1000;

        int count = groupBuyingDao.reduceNumber(groupBuyingId, nowTime);

        System.out.println("count:" + count);
    }

    @Test
    public void testQueryById() throws Exception {
        long groupBuyingId = 1000;
        GroupBuying groupBuying = groupBuyingDao.queryById(groupBuyingId);
        System.out.println("团购商品:" + groupBuying);

    }

    @Test
    public void testQueryAll() throws Exception {
        List<GroupBuying> groupBuyings = groupBuyingDao.queryAll(0, 10);
        groupBuyings.forEach(new Consumer<GroupBuying>() {
            @Override
            public void accept(GroupBuying groupBuying) {
                System.out.println(groupBuying);
            }
        });
    }
}