package com.essence.api.configuration;


import com.google.common.base.CaseFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

@Component
public class SnakeCaseFilter extends OncePerRequestFilter {

    public static class FilteredRequest extends HttpServletRequestWrapper {

        public FilteredRequest(HttpServletRequest request) {
            super(request);
        }

        @Override
        public String getParameter(String name) {
            name = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name);
            return super.getParameter(name);
        }

        @Override
        public String[] getParameterValues(String name) {
            name = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name);
            return super.getParameterValues(name);
        }

        @Override
        public Enumeration<String> getParameterNames() {
            Enumeration<String> names = super.getParameterNames();
            List<String> collect = Collections.list(names).stream()
                    .map(item -> CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, item))
                    .collect(Collectors.toList());
            return new Vector<>(collect).elements();
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        doFilter(new FilteredRequest(httpServletRequest), httpServletResponse, filterChain);
    }
}
