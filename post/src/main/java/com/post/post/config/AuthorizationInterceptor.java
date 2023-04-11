package com.post.post.config;

import com.post.post.sevice.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    private final AuthService authService;
    public AuthorizationInterceptor(AuthService authService){
        this.authService = authService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer "))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "NoBearerToken");

        request.setAttribute("user", authService.getUserFromToken(authorizationHeader.substring(7)));

        return true;
    }
}
