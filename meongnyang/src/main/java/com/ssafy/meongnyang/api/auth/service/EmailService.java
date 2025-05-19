package com.ssafy.meongnyang.api.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class EmailService {
// 예외처리 고민 필요
    private final JavaMailSender mailSender;
    private final EmailVerificationStore verificationStore;

    public void sendVerificationCode(String email) {
        String code = generateCode(); // 6자리 인증코드 생성
        verificationStore.saveCode(email, code, Duration.ofMinutes(5)); // 5분간 유효

        // 메일 전송
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("[멍냥코치] 이메일 인증 코드");
        message.setText("인증 코드는 " + code + " 입니다.\n\n5분 이내에 입력해주세요.");

        mailSender.send(message);
    }

    private String generateCode() {
        return String.valueOf((int)(Math.random() * 900000) + 100000); // 6자리 숫자
    }
}
