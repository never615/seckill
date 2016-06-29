package org.seckill.dao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 配置文件
 * Created by never615 on 6/29/16.
 */
@Component("config")
public class Config {

    @Value("${config.imageUrl}")
    private String imageUrl;

    @Value("${config.pageItems}")
    private int pageItems;

    public String getImageUrl() {
        return imageUrl;
    }



    public int getPageItems() {
        return pageItems;
    }


}
