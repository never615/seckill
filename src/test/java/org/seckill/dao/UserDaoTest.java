package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by never615 on 6/20/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {
                "classpath:spring/spring-dao.xml",
                "classpath:spring/spring-service.xml"
        }
)
public class UserDaoTest {
    @Resource
    private UserDao userDao;

    @Test
    public void testIsExistUser() throws Exception {
        long userId=1;//713
        long count = userDao.isExistUser(userId);
        System.out.println("count:"+count);
    }
}