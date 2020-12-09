package com.essence.api.auth;

import com.essence.api.auth.tokens.AppToken;
import com.essence.api.services.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@AllArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (Objects.isNull(request.getCookies())) {
            response.sendError(SC_UNAUTHORIZED, "Token is not set.");
            return;
        }
        String token = Stream.of(request.getCookies())
                .filter(item -> Objects.equals(item.getName(), "jwt_token_cookie"))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);

        if (StringUtils.isEmpty(token)) {
            response.sendError(SC_UNAUTHORIZED, "Token is not set.");
            return;
        }

        try {
            String xsrf = request.getHeader("X-XSRF-TOKEN");
            jwtService.checkToken(token, xsrf);
            AppToken appToken = jwtService.parseToken(token);
            SecurityContextHolder.getContext().setAuthentication(new UserAuthentication(appToken));
        } catch (MalformedJwtException ex) {
            response.sendError(SC_UNAUTHORIZED, "Malformed token.");
            return;
        } catch (SignatureException ex) {
            response.sendError(SC_UNAUTHORIZED, "Invalid token.");
            return;
        } catch (ExpiredJwtException ex) {
            response.sendError(SC_UNAUTHORIZED, "Token is expired.");
            return;
        } catch (Exception ex) {
            response.sendError(SC_UNAUTHORIZED, ex.getMessage());
            return;
        }
        filterChain.doFilter(request, response);
    }
}
