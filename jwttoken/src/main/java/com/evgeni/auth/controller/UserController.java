package com.evgeni.auth.controller;

import com.evgeni.auth.model.vo.UserLoginVO;
import com.evgeni.auth.model.vo.UserTokenVO;
import com.evgeni.auth.security.token.TokenUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;

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
            String token = TokenUtils.generateToken(login);
            UserTokenVO tokenVO = new UserTokenVO();
            tokenVO.setToken(token);
            return tokenVO;
        }
        throw new RuntimeException(new ServletException("invalid login"));
    }
}
