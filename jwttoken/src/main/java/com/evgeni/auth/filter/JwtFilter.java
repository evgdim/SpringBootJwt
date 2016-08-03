package com.evgeni.auth.filter;

import com.evgeni.auth.model.vo.JwtPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
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
    private static final String AUTH_HEADER = "Authorization";
    public static final String SECRET_KEY = "secretkey";
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final String authHeader = request.getHeader(AUTH_HEADER);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            ((HttpServletResponse)servletResponse)
                    .sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header.");
            return;
        }

        final String token = authHeader.substring(7); // The part after "Bearer ";

        try {
            final Claims claims = Jwts.parser().setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token).getBody();
            JwtPrincipal principal = new JwtPrincipal();
            principal.setClaims(claims);
            principal.setUsername("get from token");
            request.setAttribute("jwtPrincipal", principal);
        }
        catch (final SignatureException e) {
            ((HttpServletResponse)servletResponse)
                    .sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token.");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
