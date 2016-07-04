package org.seckill.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.postgresql.util.Base64;
import org.seckill.dto.ApiResult;
import org.seckill.dto.GroupBuyingDto;
import org.seckill.dto.GroupBuyingExecution;
import org.seckill.enums.GroupBuyingStateEnum;
import org.seckill.enums.RequestStateEnum;
import org.seckill.exception.GroupBuyingCloseException;
import org.seckill.exception.GroupBuyingException;
import org.seckill.exception.OutOfGroupBuyingLimitException;
import org.seckill.exception.UserIntegralNotEnoughException;
import org.seckill.service.GroupBuyingService;
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
 * 团购
 * Created by never615 on 6/16/16.
 */
@Controller
@RequestMapping("/groupbuying")
public class GroupBuyingController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());


    @Autowired private GroupBuyingService groupBuyingService;
    @Autowired private UserService userService;


    /**
     * 请求团购商品列表
     *
     * @param model
     * @param page  页数
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model, @RequestParam("page") int page) {

        // FIXME: 6/16/16 一页的数量使用配置文件
        long offset = (page - 1) * 20;
        long limit = 20;
        model.addAttribute("list", groupBuyingService.getGroupBuyingList(offset, limit));
        return "groupbuying/list";
    }

    /**
     * 获取一个团购详情
     *
     * @param groupBuyingId
     * @param model
     * @return
     */
    @RequestMapping(value = "{groupBuyingId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("groupBuyingId") Long groupBuyingId, Model model) {
        if (groupBuyingId == null) {
            return "redirect:/groupbuying/list";
        }
        GroupBuyingDto groupBuyingDto = groupBuyingService.getById(groupBuyingId);
        if (groupBuyingDto == null) {
            return "forward:/groupbuying/list";
        }
        model.addAttribute("groupbuying", groupBuyingDto);
        return "groupbuying/detail";
    }


    /**
     * 团购执行方法.
     *
     * @param groupbuyingId 团购商品ID
     * @return
     */
    @RequestMapping(value = "{groupbuyingId}/execution", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ApiResult<GroupBuyingExecution> execute(@PathVariable("groupbuyingId") Long groupbuyingId,
                                                   @RequestHeader("Authorization") String authorization
    ) {

        // FIXME: 6/20/16 token校验待完成
        String[] tokens = authorization.split("\\.");
        String json = new String(Base64.decode(tokens[1])) + "\"}";

        String[] split = json.split(":");

        long userId=Long.valueOf(split[1]);
        if (userService.isUserExist(userId)) {
            try {
                GroupBuyingExecution groupBuyingExecution = groupBuyingService.executeGroupBuying(groupbuyingId, userId);
                return new ApiResult<GroupBuyingExecution>(RequestStateEnum.SUCCESS, groupBuyingExecution);
            } catch (GroupBuyingCloseException e) {
                return new ApiResult<GroupBuyingExecution>(RequestStateEnum.SUCCESS, new GroupBuyingExecution(GroupBuyingStateEnum.END));
            } catch (OutOfGroupBuyingLimitException e) {
                return new ApiResult<GroupBuyingExecution>(RequestStateEnum.SUCCESS, new GroupBuyingExecution(GroupBuyingStateEnum.OUT_LIMIT));
            } catch (GroupBuyingException e) {
                return new ApiResult<GroupBuyingExecution>(RequestStateEnum.SUCCESS, new GroupBuyingExecution(GroupBuyingStateEnum.INNER_ERROR));
            } catch (UserIntegralNotEnoughException e) {
                return new ApiResult<GroupBuyingExecution>(RequestStateEnum.SUCCESS, new GroupBuyingExecution(GroupBuyingStateEnum.FAIL_REDUCE_INTEGRAL));
            }
        } else {
            //用户不存在
            return new ApiResult<GroupBuyingExecution>(RequestStateEnum.USER_INEXISTENCE);
        }


//        ObjectMapper objectMapper = new ObjectMapper();


//        try {
//            JsonNode jsonNode = objectMapper.readTree(json);
//            long userId = jsonNode.get("sub").asLong();
//
//            if (userService.isUserExist(userId)) {
//                try {
//                    GroupBuyingExecution groupBuyingExecution = groupBuyingService.executeGroupBuying(groupbuyingId, userId);
//                    return new ApiResult<GroupBuyingExecution>(RequestStateEnum.SUCCESS, groupBuyingExecution);
//                } catch (GroupBuyingCloseException e) {
//                    return new ApiResult<GroupBuyingExecution>(RequestStateEnum.SUCCESS, new GroupBuyingExecution(GroupBuyingStateEnum.END));
//                } catch (OutOfGroupBuyingLimitException e) {
//                    return new ApiResult<GroupBuyingExecution>(RequestStateEnum.SUCCESS, new GroupBuyingExecution(GroupBuyingStateEnum.OUT_LIMIT));
//                } catch (GroupBuyingException e) {
//                    return new ApiResult<GroupBuyingExecution>(RequestStateEnum.SUCCESS, new GroupBuyingExecution(GroupBuyingStateEnum.INNER_ERROR));
//                } catch (UserIntegralNotEnoughException e) {
//                    return new ApiResult<GroupBuyingExecution>(RequestStateEnum.SUCCESS, new GroupBuyingExecution(GroupBuyingStateEnum.FAIL_REDUCE_INTEGRAL));
//                }
//            } else {
//                //用户不存在
//                return new ApiResult<GroupBuyingExecution>(RequestStateEnum.USER_INEXISTENCE);
//            }
//        } catch (IOException e) {
//            return new ApiResult<GroupBuyingExecution>(RequestStateEnum.TOKEN_EXCEPTION);
//        }
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
}
