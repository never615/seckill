package org.seckill.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.postgresql.util.Base64;
import org.seckill.dto.ApiResult;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillDto;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.enums.RequestStateEnum;
import org.seckill.dao.config.Config;
import org.seckill.service.SeckillService;
import org.seckill.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;

/**
 * 秒杀
 * Created by never615 on 6/16/16.
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired private SeckillService seckillService;
    @Autowired private UserService userService;
    @Autowired Config config;


    /**
     * 请求秒杀商品列表
     *
     * @param model
     * @param page  页数
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model, @RequestParam("page") int page) {

        // FIXME: 6/16/16 一页的数量使用配置文件
        long offset = (page - 1) * config.getPageItems();
        long limit = config.getPageItems();
        model.addAttribute("list", seckillService.getSeckillList(offset, limit));
        return "seckill/list";
    }

    /**
     * 获取一个秒杀详情
     *
     * @param seckillId
     * @param model
     * @return
     */
    @RequestMapping(value = "{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
        if (seckillId == null) {
            return "redirect:/seckill/list";
        }
        SeckillDto seckillDto = seckillService.getById(seckillId);
        if (seckillDto == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill", seckillDto);
        return "seckill/detail";
    }


    /**
     * 暴露秒杀地址接口
     *
     * @param seckillId 秒杀id
     * @return
     */
    @RequestMapping(value = "{seckillId}/exposer", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ApiResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId) {
        ApiResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new ApiResult<Exposer>(RequestStateEnum.SUCCESS, exposer);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            result = new ApiResult<Exposer>(RequestStateEnum.INNER_ERROR, e.getMessage());
        }
        return result;
    }

    /**
     * 秒杀执行方法.
     *
     * @param seckillId 秒杀商品ID
     * @param md5       秒杀Key
     * @return
     */
    @RequestMapping(value = "{seckillId}/{md5}/execution", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ApiResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                               @RequestHeader("Authorization") String authorization,
                                               @PathVariable("md5") String md5) {

        // FIXME: 6/20/16 token校验待完成
        String[] tokens = authorization.split("\\.");
        String json = new String(Base64.decode(tokens[1])) + "\"}";

        ObjectMapper objectMapper = new ObjectMapper();


        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            long userId = jsonNode.get("sub").asLong();

            if (userService.isUserExist(userId)) {
                SeckillExecution seckillExecution = seckillService.executeSeckillProcedure(seckillId, userId, md5);

                return new ApiResult<SeckillExecution>(RequestStateEnum.SUCCESS, seckillExecution);
            } else {
                //用户不存在
                return new ApiResult<SeckillExecution>(RequestStateEnum.USER_INEXISTENCE);
            }
        } catch (IOException e) {
            return new ApiResult<SeckillExecution>(RequestStateEnum.TOKEN_EXCEPTION);
        }
    }

    /**
     * 获取系统当前时间
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "time/now", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ApiResult<Long> execute(Model model) {
        return new ApiResult<Long>(RequestStateEnum.SUCCESS, new Date().getTime());
    }

//
//    @RequestMapping(value = "test", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
//    @ResponseBody
//    public String test(Model model) {
//        String imageUrl = config.getImageUrl();
//        return imageUrl;
////        System.out.println(imageUrl);
//    }
}
