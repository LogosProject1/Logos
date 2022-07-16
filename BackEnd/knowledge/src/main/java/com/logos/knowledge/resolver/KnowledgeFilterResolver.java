package com.logos.knowledge.resolver;

import com.logos.knowledge.dto.KnowledgeFilterDto;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class KnowledgeFilterResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(KnowledgeFilter.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        KnowledgeFilterDto knowledgeFilterDto = new KnowledgeFilterDto();

        knowledgeFilterDto.setCategory(webRequest.getParameter("category"));
        knowledgeFilterDto.setStartTime(webRequest.getParameter("startTime"));
        knowledgeFilterDto.setEndTime(webRequest.getParameter("endTime"));
        knowledgeFilterDto.setMinPrice(webRequest.getParameter("minPrice"));
        knowledgeFilterDto.setMaxPrice(webRequest.getParameter("maxPrice"));

        return knowledgeFilterDto;
    }
}
