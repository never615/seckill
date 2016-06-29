package org.seckill.dto.mapper;

import org.seckill.dao.config.Config;
import org.seckill.dto.GroupBuyingDto;
import org.seckill.dto.SuccessGroupBuyingDto;
import org.seckill.entity.SuccessGroupBuying;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by never615 on 6/29/16.
 */
@Component
public class SuccessGroupBuyingDtoMapper {


    @Autowired MapperUtils mapperUtils;
    @Autowired Config config;


    public SuccessGroupBuyingDto mapper(SuccessGroupBuying successGroupBuying) {

        SuccessGroupBuyingDto successGroupBuyingDto = new SuccessGroupBuyingDto();
        GroupBuyingDto groupBuyingDto = new GroupBuyingDto();

        BeanUtils.copyProperties(successGroupBuying, successGroupBuyingDto);
        BeanUtils.copyProperties(successGroupBuying.getGroupBuying(), groupBuyingDto);

        String imagesStr = groupBuyingDto.getImages();
        List<String> imageUrlList = mapperUtils.getImageUrlList(imagesStr);

        groupBuyingDto.setLogo(config.getImageUrl() + groupBuyingDto.getLogo());
        groupBuyingDto.setImageUrls(imageUrlList);
        successGroupBuyingDto.setGroupBuyingDto(groupBuyingDto);


        return successGroupBuyingDto;
    }

}
