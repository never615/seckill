package org.seckill.dto.mapper;

import org.seckill.dao.config.Config;
import org.seckill.dto.GroupBuyingDto;
import org.seckill.entity.GroupBuying;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by never615 on 6/29/16.
 */
@Component
public class GroupBuyingDtoMapper {

    @Autowired Config config;
    @Autowired MapperUtils mapperUtils;


    public GroupBuyingDto mapper(GroupBuying groupBuying) {

        GroupBuyingDto groupBuyingDto = new GroupBuyingDto();

        BeanUtils.copyProperties(groupBuying, groupBuyingDto);

        String images = groupBuyingDto.getImages();
        List<String> imageUrlList = mapperUtils.getImageUrlList(images);

        groupBuyingDto.setImageUrls(imageUrlList);
        groupBuyingDto.setLogo(config.getImageUrl() + groupBuyingDto.getLogo());
        return groupBuyingDto;
    }


    public List<GroupBuyingDto> mapperList(List<GroupBuying> groupBuyings) {
        List<GroupBuyingDto> groupBuyingDtos = new ArrayList<GroupBuyingDto>();
        for (GroupBuying groupBuying : groupBuyings) {
            groupBuyingDtos.add(mapper(groupBuying));
        }
        return groupBuyingDtos;
    }
}
