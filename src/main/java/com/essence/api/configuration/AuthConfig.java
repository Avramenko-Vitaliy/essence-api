package com.essence.api.configuration;

import com.essence.api.auth.JwtAuthFilter;
import com.essence.api.controllers.Api;
import com.essence.api.services.JwtService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.Set;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class AuthConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().logout().disable();
    }

    @Bean
    @Order(1)
    public FilterRegistrationBean<JwtAuthFilter> jwtFilter(JwtService jwtService) {
        FilterRegistrationBean<JwtAuthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtAuthFilter(jwtService));
        registrationBean.addUrlPatterns(Api.ROOT_PATH + Api.Session.LOGOUT);
        return registrationBean;
    }
}
