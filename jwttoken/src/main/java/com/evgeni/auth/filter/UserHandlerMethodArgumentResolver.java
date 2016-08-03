package com.evgeni.auth.filter;

import com.evgeni.auth.model.vo.JwtPrincipal;
import io.jsonwebtoken.Claims;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by evgeni on 8/3/2016.
 */
public class UserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver{
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(JwtPrincipal.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        JwtPrincipal principal = (JwtPrincipal)servletRequest.getAttribute("jwtPrincipal");

        return principal;
    }
}
