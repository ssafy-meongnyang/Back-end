package com.ssafy.meongnyang.api.user.service;

import com.ssafy.meongnyang.api.user.domain.User;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
    public CustomUserDetails(User user) {
    }
}
