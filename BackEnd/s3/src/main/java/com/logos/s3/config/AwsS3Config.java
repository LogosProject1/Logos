package com.logos.s3.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AwsS3Config {
    private final AwsS3Properties awsS3Properties;

    @Bean
    public AmazonS3 amazonS3(){
        AWSCredentials credentials
    }
}
