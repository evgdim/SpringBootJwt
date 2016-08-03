package com.evgeni.auth.controller;

import com.evgeni.auth.model.vo.JwtPrincipal;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * Created by evgeni on 8/3/2016.
 */
@RestController
@RequestMapping("/api")
public class ApiController {
    @RequestMapping("/test")
    public JwtPrincipal test(JwtPrincipal principal){
        return principal;
    }
}
