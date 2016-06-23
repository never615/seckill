package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

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

    }

    @Test
    public void testQueryByIdWithGroupBuying() throws Exception {

    }

    @Test
    public void testQueryByUserIdAndGroupBuyingId() throws Exception {

    }

    @Test
    public void testQueryByUserIdWithGroupBuying() throws Exception {

    }

    @Test
    public void testUpdateVerificationCode() throws Exception {

    }
}