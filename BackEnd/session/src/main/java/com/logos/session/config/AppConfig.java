package com.logos.session.config;

import io.openvidu.java.client.OpenVidu;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    private final AppProperties appProperties;
    @Bean
    public OpenVidu openVidu(){
        return new OpenVidu(appProperties.getPROTOCOL()+appProperties.getOPENVIDU_HOST()+":"+appProperties.getOPENVIDU_PORT(), appProperties.getSECRET());
    }
}
