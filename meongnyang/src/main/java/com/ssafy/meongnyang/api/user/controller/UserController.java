package com.ssafy.meongnyang.api.user.controller;

import com.ssafy.meongnyang.api.user.domain.User;
import com.ssafy.meongnyang.api.user.service.UserService;
import com.ssafy.meongnyang.global.response.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ApiResponseDto<?> register(@RequestBody User user) {

        return null;
    }
}
