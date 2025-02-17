package com.cdac.trustvault.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
             throws IOException, ServletException {
         String token = jwtTokenProvider.resolveToken(request);
         
         if (token != null) {
             if (jwtTokenProvider.validateToken(token)) {
                 Authentication auth = jwtTokenProvider.getAuthentication(token);
                 SecurityContextHolder.getContext().setAuthentication(auth);
                 System.out.println("Token is valid. Authentication set.");
             } else {
                 System.out.println("Token validation failed.");
             }
         } else {
             System.out.println("No token found.");
         }
         
         filterChain.doFilter(request, response);
     }

}