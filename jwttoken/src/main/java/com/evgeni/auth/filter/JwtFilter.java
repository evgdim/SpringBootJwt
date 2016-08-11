package com.evgeni.auth.filter;

import com.evgeni.auth.model.vo.JwtPrincipal;
import com.evgeni.auth.security.token.TokenUtils;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by evgeni on 8/3/2016.
 */
public class JwtFilter extends GenericFilterBean {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final String authHeader = request.getHeader(TokenUtils.AUTH_HEADER);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            ((HttpServletResponse)servletResponse)
                    .sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header.");
            return;
        }

        try {
            JwtPrincipal principal = TokenUtils.parseToken(authHeader);
            request.setAttribute("jwtPrincipal", principal);
        }
        catch (final SignatureException e) {
            logger.warn("[doFilter] SignatureException",e);
            ((HttpServletResponse)servletResponse)
                    .sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token.");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
