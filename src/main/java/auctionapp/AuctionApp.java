package auctionapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import auctionapp.security.JwtFilter;

import java.util.Collections;

@SpringBootApplication
public class AuctionApp {
    public static void main(String[] args) {
        SpringApplication.run(AuctionApp.class);
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new JwtFilter());
        filterRegistrationBean.addUrlPatterns("/auctionAPI/*");
        filterRegistrationBean.addUrlPatterns("/userAPI/*");
        filterRegistrationBean.addUrlPatterns("/itemAPI/*");
        filterRegistrationBean.addUrlPatterns("/bidAPI/*");
        filterRegistrationBean.addUrlPatterns("/personAPI/*");
        return filterRegistrationBean;
    }
}
