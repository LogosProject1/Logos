package com.logos.point.service;

import java.util.Map;

public interface JwtService {
    String create(Map<String,Object> userInfoMap, String subject);
    byte[] generateKey();
    boolean isUsable(String jwt);
    Map<String,Object> resolveJwt();
    String getName();
    String getEmail();
    String getType();
}
