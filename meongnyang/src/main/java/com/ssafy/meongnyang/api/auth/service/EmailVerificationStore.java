package com.ssafy.meongnyang.api.auth.service;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class EmailVerificationStore {
    private final Map<String, AuthCodeInfo> authCodeMap = new ConcurrentHashMap<>();
    private final Set<String> verifiedEmails = ConcurrentHashMap.newKeySet();

    public void saveCode(String email, String code, Duration validTime) {
        authCodeMap.put(email, new AuthCodeInfo(code, LocalDateTime.now().plus(validTime)));
    }

    public boolean verify(String email, String code) {
        AuthCodeInfo info = authCodeMap.get(email);
        if (info == null || info.isExpired() || !info.code().equals(code)) {
            return false;
        }

        verifiedEmails.add(email);
        authCodeMap.remove(email);
        return true;
    }

    public boolean isVerified(String email) {
        return verifiedEmails.contains(email);
    }

    // 내부 클래스
    private record AuthCodeInfo(String code, LocalDateTime expiresAt) {
        public boolean isExpired() {
            return LocalDateTime.now().isAfter(expiresAt);
        }
    }
}
