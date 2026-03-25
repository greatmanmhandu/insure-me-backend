//package com.vozhe.jwt.config;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import javax.servlet.DispatcherType;
//@Configuration
//public class FilterConfig {
//
//    @Bean
//    public FilterRegistrationBean<HttpToHttpsRedirectFilter> httpToHttpsRedirectFilterRegistration() {
//        FilterRegistrationBean<HttpToHttpsRedirectFilter> registration = new FilterRegistrationBean<>();
//        registration.setFilter(new HttpToHttpsRedirectFilter());
//        registration.addUrlPatterns("/*");
//        registration.setDispatcherTypes(DispatcherType.REQUEST);
//        registration.setOrder(1); // Set the order if you have multiple filters
//        return registration;
//    }
//}
