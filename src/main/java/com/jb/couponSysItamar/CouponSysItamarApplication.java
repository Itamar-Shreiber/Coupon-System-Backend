package com.jb.couponSysItamar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"com.jb.couponSysItamar"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "com.jb.couponSysItamar.clr.off.*"))
@EnableScheduling
public class CouponSysItamarApplication {
    public static void main(String[] args) {
        SpringApplication.run(CouponSysItamarApplication.class, args);
        System.out.println("yalla balagan!!");
    }

}
