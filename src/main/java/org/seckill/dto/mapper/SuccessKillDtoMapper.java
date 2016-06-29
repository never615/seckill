package org.seckill.dto.mapper;

import org.seckill.dao.config.Config;
import org.seckill.dto.SeckillDto;
import org.seckill.dto.SuccessKilledDto;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * SuccessKillDto转SuccessKilledDto
 * Created by never615 on 6/29/16.
 */
@Component
public class SuccessKillDtoMapper {


    @Autowired MapperUtils mapperUtils;
    @Autowired Config config;


    /**
     * 转换数据层的SuccessKilled 为业务层的SuccessKilledDto
     *
     * @param successKilled
     * @return
     */
    public SuccessKilledDto mapper(SuccessKilled successKilled) {

        SuccessKilledDto successKilledDto = new SuccessKilledDto();
        SeckillDto seckillDto = new SeckillDto();


        BeanUtils.copyProperties(successKilled, successKilledDto);
        BeanUtils.copyProperties(successKilled.getSeckill(), seckillDto);


        //把数据库存储的以逗号分隔的图片变成list的形式,并且拼接图片前缀
        Seckill seckill = successKilled.getSeckill();

        List<String> imageUrlList = mapperUtils.getImageUrlList(seckill.getImages());

        seckillDto.setImageUrls(imageUrlList);
        seckillDto.setLogo(config.getImageUrl() + seckillDto.getLogo());

        successKilledDto.setSeckillDto(seckillDto);

        return successKilledDto;
    }
}
