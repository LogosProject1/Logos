package com.logos.rating.service;


import com.logos.rating.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final JwtProperties jwtProperties;
    @Override
    public String create(Map<String, Object> userInfoMap, String subject) {
        String jwt = Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setHeaderParam("regDate",System.currentTimeMillis())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * jwtProperties.getEXPIRE_MINUTES()))
                .setSubject(subject)
                .claim("email",userInfoMap.get("email"))
                .claim("name",userInfoMap.get("name"))
                .claim("type",userInfoMap.get("type"))
                .signWith(SignatureAlgorithm.HS512,this.generateKey())
                .compact();
        return jwt;
    }

    @Override
    public byte[] generateKey() {
        byte[] key = jwtProperties.getSECRET_KEY().getBytes(StandardCharsets.UTF_8);
        return key;
    }

    @Override
    public boolean isUsable(String jwt) {
        try{
            Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
            return true;
        }
        catch (Exception e){
            log.error("Jwt isUsable Method error : {}",e.getMessage());
            return false;
        }
    }

    @Override
    public Map<String, Object> resolveJwt() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        String jwt = request.getHeader(jwtProperties.getHEADER_AUTH()).substring(7);
        Jws<Claims> claims = null;
        try{
            claims = Jwts.parser()
                    .setSigningKey(jwtProperties.getSECRET_KEY()
                            .getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(jwt);
        }
        catch (Exception e){
            log.error("Jwt get error : {}",e.getMessage());
        }
        Map<String, Object> value = claims.getBody();
        log.info("value : {}", value);
        return value;
    }

    public String getName(){
        return (String)this.resolveJwt().get("name");
    }

    public String getEmail(){
        return (String)this.resolveJwt().get("email");
    }

    public String getType(){
        return (String)this.resolveJwt().get("type");
    }
}
