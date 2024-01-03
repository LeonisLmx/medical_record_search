package com.medical.record.config;

import com.medical.record.util.TokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author urey.liu
 * @description
 * @date 2023/5/24 11:32 上午
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean authFilterRegistrationBean(){
        FilterRegistrationBean registration = new FilterRegistrationBean<>(new TokenFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }
}
