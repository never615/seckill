package org.seckill;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.config.Config;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by never615 on 6/29/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {
                "classpath:spring/spring-dao.xml",
        }
)
public class ConfigTest {
    @Resource private Config config;

    @Test
    public void test1(){
        String imageUrl = config.getImageUrl();
        System.out.println("imageUrl:"+imageUrl);

        System.out.println(config.getPageItems());
    }




}
