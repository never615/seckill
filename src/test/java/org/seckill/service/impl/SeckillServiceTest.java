package org.seckill.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.postgresql.util.Base64;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillDto;
import org.seckill.dto.SeckillExecution;
import org.seckill.service.SeckillService;
import org.seckill.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {
                "classpath:spring/spring-dao.xml",
                "classpath:spring/spring-service.xml"
        }
)
public class SeckillServiceTest {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Resource private SeckillService seckillService;
    @Resource private UserService userService;

    @Test
    public void testGetById() throws Exception {
        long id = 1000;
        SeckillDto seckill = seckillService.getById(id);
        System.out.println(seckill);
    }

    @Test
    public void testGetSeckillList() throws Exception {
        System.out.println(seckillService.getSeckillList(0l, 0,20));
    }

    /**
     * 集成测试：秒杀完整流程，可重复执行
     */
    @Test
    public void testSeckillLogic() {

//        long id = 1001;
//        Exposer exposer = seckillService.exportSeckillUrl(id);
//        LOG.info("exposer={}",exposer);
//        if (exposer.isExposed()) {
//
//            long phone = 15821739225L;
//            String md5 = exposer.getMd5();
//
//            try {
//                SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
//                LOG.info("result={}",seckillExecution);
//            } catch (RepeatKillException e) {
//                LOG.error(e.getMessage());
//            } catch (SeckillCloseException e) {
//                LOG.error(e.getMessage());
//            }
//
//        } else {
//            LOG.warn("秒杀未开始：{}",exposer.toString());
//        }

    }

    @Test
    public void testExportSeckillUrl() throws Exception {
        long id = 1000;
        for (int i=0;i<=30;i++){
            Exposer exposer = seckillService.exportSeckillUrl(id);
            System.out.println(exposer);
        }
      //  Exposer exposer = seckillService.exportSeckillUrl(id);
       // System.out.println(exposer);
        //LOG.info("exposer={}", exposer.toString());
//     Exposer{exposed=true, md5='be3d9cdd642d64f8ed97eb05e93b9628', seckillId=1000, now=0, start=0, end=0}
    }

//    @Test
//    public void testExecuteSeckill() throws Exception {
////        long id = 1000;
////        long phone = 15821739223L;
////
////        String md5 = "be3d9cdd642d64f8ed97eb05e93b9628";
////
////        SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
////
////        LOG.info("seckillExecution={}",seckillExecution);
//
//    }

    @Test
    public void testExecuteSeckillProcedure() {
        System.out.println("-------------------");
        long seckillId = 1002L;
        long userId = 713;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if (exposer.isExposed()) {
            String md5 = exposer.getMd5();
            SeckillExecution seckillExecution = seckillService
                    .executeSeckillProcedure(seckillId, userId, md5);
            System.out.println("seckillExecution:" + seckillExecution);
//            LOG.info(seckillExecution.getStateInfo());
        }
    }

    @Test
    public void testExecuteSeckill() {
        System.out.println("-------------------");


        String authorization = "Bearer{eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjcxMywiaXNzIjoiaHR0cDpcL1wvYXBpLmlmZW5nZ3VvLmNvbTo4MVwvYXBpXC9hdXRoXC9sb2dpbiIsImlhdCI6MTQ2NjEzNzUyNiwiZXhwIjoxNDczOTEzNTI2LCJuYmYiOjE0NjYxMzc1MjYsImp0aSI6ImEyZDhiMzkxODY4MjU2NzQ5YWY5Yzk5NmQwNDYxYmIxIn0.wfvVmWE9zaEQw-23aM7oQiXiPWwzDubMcG5rsB2ns-4}";
        long seckillId = 1000;

        Exposer exposer = seckillService.exportSeckillUrl(seckillId);

        if (exposer.isExposed()) {
            String md5 = exposer.getMd5();


            String[] tokens = authorization.split("\\.");
            String json = new String(Base64.decode(tokens[1])) + "\"}";

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode jsonNode = objectMapper.readTree(json);
                long userId = jsonNode.get("sub").asLong();
                System.out.println("-------------------userId:"+userId);
                if (userService.isUserExist(userId)) {
                    SeckillExecution seckillExecution = seckillService.executeSeckillProcedure(seckillId, userId, md5);
                    System.out.println("-------------------seckillExecution:" + seckillExecution);
                } else {
                    //用户不存在
                    System.out.println("-------------------用户不存在");
                }
            } catch (IOException e) {
                System.out.println("-------------------IO异常");
            }
        }
    }

}
