package org.seckill.dto.mapper;

import org.seckill.dao.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * mapper 工具
 * Created by never615 on 6/29/16.
 */
@Component
public class MapperUtils {
    @Autowired Config config;

    public  List<String> getImageUrlList(String imagesStr) {
        if (imagesStr != null) {
            List<String> imageUrlList = new ArrayList<String>();
            for (String image : Arrays.asList(imagesStr.split(","))) {
                imageUrlList.add(config.getImageUrl() + image);
            }
            return imageUrlList;
        } else {
            return null;
        }
    }
}
