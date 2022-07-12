package com.logos.auth.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@ConfigurationProperties("jwt")
@RequiredArgsConstructor
public class JwtProperties {
    private final String SECRET_KEY;
    private final String HEADER_AUTH;
    private final int EXPIRE_MINUTES;
}
