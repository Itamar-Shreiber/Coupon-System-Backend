package com.jb.couponSysItamar.config;


import com.jb.couponSysItamar.security.Information;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by kobis on 29 Dec, 2022
 */
@Configuration
public class MapConfig {

    @Bean
    public Map<UUID, Information> map(){
        return new HashMap<>();
    }
}
