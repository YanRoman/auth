package com.post.post.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.post.post.entity.User;
import com.post.post.sevice.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    record RegisterRequest(String username, String password, @JsonProperty("password_confirm") String passwordConfirm){}
    record RegisterResponse(Long id, String username){}
    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest registerRequest){
        User user = authService.register(registerRequest.username, registerRequest.password, registerRequest.passwordConfirm);
        return new RegisterResponse(user.getId(), user.getUsername());
    }
    record LoginRequest(String username, String password){}
    record LoginResponse(String token){}
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpServletResponse response){
        var login = authService.login (loginRequest.username, loginRequest.password);

        Cookie cookie = new Cookie("refresh_token", login.getRefreshToken().getToken());
        cookie.setMaxAge(3600);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        return new LoginResponse(login.getAccessToken().getToken());
    }

    record UserResponse(Long id, String username){}

    @GetMapping("/user")
    public UserResponse user(HttpServletRequest request){
        System.out.println(request.getAttribute("user"));
        var user = (User) request.getAttribute("user");
        return new UserResponse(user.getId(), user.getUsername());
    }

    record RefreshResponse(String token){}
    @PostMapping("/refresh")
    public RefreshResponse refresh(@CookieValue("refresh_token") String refreshToken){
        return new RefreshResponse(authService.refreshAccess(refreshToken).getAccessToken().getToken());
    }

    record LogoutResponse(String message){}
    @PostMapping("/logout")
    public LogoutResponse logout(HttpServletResponse response){
        Cookie cookie = new Cookie("refresh_token", null);
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);

        response.addCookie(cookie);

        return new LogoutResponse("success");
    }
}
