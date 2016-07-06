package org.seckill.dto.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.seckill.dao.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * mapper 工具
 * Created by never615 on 6/29/16.
 */
@Component
public class MapperUtils {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired Config config;

    public List<String> getImageUrlList(String imagesStr) {
        if (imagesStr != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String[] strings = objectMapper.readValue(imagesStr, String[].class);

                List<String> imageUrlList = new ArrayList<String>();


                for (String image : Arrays.asList(strings)) {
//                for (String image : Arrays.asList(imagesStr.split(","))) {
                    imageUrlList.add(config.getImageUrl() + image);
                }
                return imageUrlList;

            } catch (IOException e) {
                LOG.error("json 解析失败:" + e);
                return null;
            }
        } else {
            return null;
        }
    }
}
