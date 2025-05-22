package com.ssafy.meongnyang.global.common.annotation;

import com.ssafy.meongnyang.api.auth.security.CustomUserDetails;
import java.security.Principal;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class UserIdResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Long.class)
                && parameter.hasParameterAnnotation(UserId.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {
        Authentication authentication = (Authentication) webRequest.getUserPrincipal();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null; // or throw exception
        }

        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        return principal.getUserId();
    }
}
