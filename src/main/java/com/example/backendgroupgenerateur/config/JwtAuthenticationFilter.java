package com.example.backendgroupgenerateur.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.backendgroupgenerateur.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtils jwtUtils;
@Override
protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    String path = request.getServletPath();
        System.out.println("Request path: " + path + " => shouldNotFilter: " + path.startsWith("/auth/"));

    return path.startsWith("/auth/");
}

    @Autowired
    @Lazy
    private UserService userService;  // injection en lazy pour casser la dépendance circulaire

@Override
protected void doFilterInternal(HttpServletRequest request,
                                HttpServletResponse response,
                                FilterChain filterChain)
        throws ServletException, IOException {

    final String authHeader = request.getHeader("Authorization");
    String token = null;
    String username = null;

    System.out.println("Authorization header: " + authHeader);

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
        token = authHeader.substring(7);
        System.out.println("Token extracted: " + token);
        if (jwtUtils.validateToken(token)) {
            username = jwtUtils.getUsernameFromToken(token);
            System.out.println("Username from token: " + username);
        } else {
            System.out.println("Token validation failed");
        }
    } else {
        System.out.println("No Bearer token found in Authorization header");
    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = userService.loadUserByUsername(username);

        if (jwtUtils.validateToken(token)) {
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
            System.out.println("User authenticated: " + username);
        } else {
            System.out.println("Token invalid during authentication");
        }
    } else if (username == null) {
        System.out.println("No username extracted or user already authenticated");
    }

    filterChain.doFilter(request, response);
}
}
