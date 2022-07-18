package com.logos.session.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "openvidu")
@RequiredArgsConstructor
@Getter
public class AppProperties {
    private final String OPENVIDU_URL;
    private final String SECRET;
}
