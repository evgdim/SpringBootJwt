package com.evgeni.auth.controller;

import com.evgeni.auth.filter.JwtFilter;
import com.evgeni.auth.model.vo.UserLoginVO;
import com.evgeni.auth.model.vo.UserTokenVO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.security.Principal;
import java.util.Date;

/**
 * Created by evgeni on 8/3/2016.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping("/login")
    public UserTokenVO login(@RequestBody final UserLoginVO login){
        if("user".equals(login.getUsername()) &&
                "pass".equals(login.getPassword())){
            UserTokenVO tokenVO = new UserTokenVO();
            String token = Jwts.builder()
                            .setSubject(login.getUsername())
                            .claim("role", "USER")
                            .setIssuedAt(new Date())
                            .signWith(SignatureAlgorithm.HS256, JwtFilter.SECRET_KEY)
                            .compact();
            tokenVO.setToken(token);
            return tokenVO;
        }
        throw new RuntimeException(new ServletException("invalid login"));
    }
}
