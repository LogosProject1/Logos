package com.logos.session.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.stereotype.Component;

@Getter
@ConstructorBinding
@ConfigurationProperties("openvidu")
@RequiredArgsConstructor
public class AppProperties {
    private final String OPENVIDU_URL;
    private final String SECRET;
}
