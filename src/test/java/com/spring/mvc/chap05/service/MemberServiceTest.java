package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.request.LoginRequestDTO;
import com.spring.mvc.chap05.dto.request.SignUpRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.spring.mvc.chap05.service.LoginResult.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;


    @Test
    @DisplayName("회원정보를 전달하면 비밀번호가 암호화되어 데이터베이스에 저장될 것이다.")
    void joinTest() {
        // given
        SignUpRequestDTO dto = SignUpRequestDTO.builder()
                .account("yalu1111")
                .password("sinna12345")
                .name("김얄루")
                .email("yalu@naver.com")
                .build();

        // when
        memberService.join(dto);
        // then
    }
    
    @Test
    @DisplayName("계정명이 yalu1111인 회원의 로그인 시도결과를 상황별로 검증한다.")
    void loginTest() {
        // given
        LoginRequestDTO dto = LoginRequestDTO.builder()
                .account("yalu1111")
                .password("sinna12345")
                .build();

        // when
        LoginResult result = memberService.authenticate(dto);

        // then
        assertEquals(SUCCESS, result);

    }

}