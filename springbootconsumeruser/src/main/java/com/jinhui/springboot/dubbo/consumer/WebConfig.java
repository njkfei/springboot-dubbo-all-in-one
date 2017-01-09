package com.jinhui.springboot.dubbo.consumer;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import javax.servlet.Filter;
import me.hao0.trace.core.filter.TraceFilter;
/**
 * Created by niejinping on 2017/1/4.
 */
@Configuration
@EnableAutoConfiguration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(traceFilter());
        registration.addUrlPatterns("/user/*");
        registration.setName("traceFilter");
        return registration;
    }

    @Bean(name = "traceFilter")
    public Filter traceFilter() {
        return new TraceFilter();
    }
}
