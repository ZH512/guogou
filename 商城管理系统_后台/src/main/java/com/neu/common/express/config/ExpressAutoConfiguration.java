package com.neu.common.express.config;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.neu.common.express.ExpressService;

@Configuration
@EnableConfigurationProperties(ExpressProperties.class)
public class ExpressAutoConfiguration {

    private final ExpressProperties properties;

    public ExpressAutoConfiguration(ExpressProperties properties) {
        this.properties = properties;
    }

    @Bean
    public ExpressService expressService() {
        ExpressService expressService = new ExpressService();
        expressService.setProperties(properties);
        return expressService;
    }

}
