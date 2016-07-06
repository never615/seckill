package org.seckill.dto.mapper;

import org.seckill.dao.config.Config;
import org.seckill.dto.SeckillDto;
import org.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by never615 on 6/29/16.
 */
@Component
public class SeckillDtoMapper {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());


    @Autowired Config config;
    @Autowired MapperUtils mapperUtils;

    public SeckillDto mapper(Seckill seckill) {

        SeckillDto seckillDto = new SeckillDto();

        BeanUtils.copyProperties(seckill, seckillDto);

        String imagesStr = seckillDto.getImages();

        List<String> imageUrlList = mapperUtils.getImageUrlList(imagesStr);
        seckillDto.setImageUrls(imageUrlList);

        seckillDto.setLogo(config.getImageUrl() + seckillDto.getLogo());
        return seckillDto;
    }


    public List<SeckillDto> mapperList(List<Seckill> seckills) {
        List<SeckillDto> seckillDtos = new ArrayList<SeckillDto>();
        for (Seckill seckill : seckills) {
            seckillDtos.add(mapper(seckill));
        }
        return seckillDtos;
    }
}
