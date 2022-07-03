package com.chuwa.blog.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author b1go
 * @date 7/3/22 9:44 AM
 */
@Configuration
public class CommConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
