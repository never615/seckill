package org.seckill.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.GroupBuyingExecution;
import org.seckill.entity.GroupBuying;
import org.seckill.service.GroupBuyingService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

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
public class GroupBuyingServiceImplTest {

    @Resource GroupBuyingService groupBuyingService;

    @Test
    public void testGetGroupBuyingList() throws Exception {
        List<GroupBuying> groupBuyingList = groupBuyingService.getGroupBuyingList(0, 10);
        for (GroupBuying groupBuying : groupBuyingList) {
            System.out.println(groupBuying);
        }
    }

    @Test
    public void testGetById() throws Exception {
        GroupBuying groupBuying = groupBuyingService.getById(1000);
        System.out.println(groupBuying);
    }

    @Test
    public void testExecuteGroupBuying() throws Exception {
        long groupBuyingId = 1000;
        long userId = 713;
        GroupBuyingExecution groupBuyingExecution = groupBuyingService.executeGroupBuying(groupBuyingId, userId);
        System.out.println(groupBuyingExecution);
    }
}