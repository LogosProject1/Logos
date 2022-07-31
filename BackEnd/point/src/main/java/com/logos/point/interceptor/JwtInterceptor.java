package com.logos.point.interceptor;

import com.logos.point.config.JwtProperties;
import com.logos.point.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {
    private final JwtProperties jwtProperties;
    private final JwtService jwtService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getMethod().equals("OPTIONS")){
            //CORS OPTIONS 메소드 허용
            log.debug("if request options method is options, return true");

            return true;
        }

        if(request.getHeader(jwtProperties.getHEADER_AUTH()) == null){
            log.info("토큰 값 없음");
            throw new Exception();
        }

        final String token = request.getHeader(jwtProperties.getHEADER_AUTH()).substring(7);

        if(token != null && jwtService.isUsable(token)){
            log.info("토큰 사용 가능 : {}",token);
            request.setAttribute("Name",jwtService.getName());
            request.setAttribute("Email",jwtService.getEmail());
            request.setAttribute("Type",jwtService.getType());
            return true;
        }
        else {
            log.info("토큰 사용 불가능 : {}",token);
            throw new Exception();
        }
    }
}