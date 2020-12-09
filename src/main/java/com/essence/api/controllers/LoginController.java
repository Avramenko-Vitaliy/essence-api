package com.essence.api.controllers;

import com.essence.api.auth.tokens.AppToken;
import com.essence.api.dtos.Credentials;
import com.essence.api.services.JwtService;
import com.essence.api.services.LoginService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static com.essence.api.services.JwtService.JWT_TOKEN_COOKIE;

@RestController
@AllArgsConstructor
@RequestMapping(value = Api.ROOT_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {

    private final JwtService jwtService;
    private final LoginService loginService;

    @ApiOperation("Login user")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", required = true, paramType = "body"),
            @ApiImplicitParam(name = "password", required = true, paramType = "body")
    })
    @PostMapping(Api.Session.LOGIN)
    public AppToken login(@Validated @RequestBody Credentials credentials, HttpServletResponse response) throws JsonProcessingException {
        AppToken token = loginService.login(credentials);
        Cookie cookie = createCookie(JWT_TOKEN_COOKIE, jwtService.buildToken(token));
        response.addCookie(cookie);
        return token;
    }

    @ApiOperation("Logout")
    @ApiImplicitParams({
            @ApiImplicitParam(name = JWT_TOKEN_COOKIE, required = true, paramType = "cookie"),
            @ApiImplicitParam(name = "X-XSRF-TOKEN", required = true, paramType = "header")
    })
    @DeleteMapping(Api.Session.LOGOUT)
    public void logout(HttpServletResponse response) {
        Cookie cookie = createCookie(JWT_TOKEN_COOKIE, "deleteAll");
        response.addCookie(cookie);
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setPath("/");
        cookie.setDomain("");
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        return cookie;
    }
}
