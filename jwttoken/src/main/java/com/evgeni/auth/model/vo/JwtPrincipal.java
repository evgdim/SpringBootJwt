package com.evgeni.auth.model.vo;

import io.jsonwebtoken.Claims;
import lombok.Data;

/**
 * Created by evgeni on 8/3/2016.
 */
@Data
public class JwtPrincipal {
    private String username;
    private Claims claims;
}
