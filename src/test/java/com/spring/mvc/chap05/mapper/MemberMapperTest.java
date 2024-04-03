package com.spring.mvc.chap05.mapper;

import com.spring.mvc.chap05.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class MemberMapperTest {

    @Autowired
    MemberMapper memberMapper;

    @Test
    @DisplayName("회원가입에 성공해야한다.")
    void saveTest() {
        // given
        // when
        Member member = Member.builder()
                .account("orange1005")
                .password("loveOrange")
                .name("이가욤")
                .email("orange1005@naver.com")
                .build();

        memberMapper.save(member);

        // then
    }

    @Test
    @DisplayName("아이디가 abc1234인 계정을 조회하면 그회원의 이름은 김춘식이어야 한다.")
    void findMemberTest() {
        // given

        // when
        Member abc1234 = memberMapper.findMember("abc1234");

        String name = abc1234.getName();
        // then
        assertEquals(name, "김춘식");
    }

    @Test
    @DisplayName("계정이 abc1234일 경우 중복확인 결과값이 true여야 한다.")
    void duplicateTest() {
        // given
        String id = "abc1234";
        // when
        boolean accountFlag = memberMapper.isDuplicate("account", id);

        // then
        assertEquals(accountFlag, true);
    }

    @Test
    @DisplayName("이메일이 abd@naver.com 일 경우 중복확인 결과값이 false 여야한다.")
    void duplicateEmailTest() {
        // given
        String email = "abd@naver.com";

        // when
        boolean emailflag = memberMapper.isDuplicate("email", email);

        // then
        assertEquals(emailflag, false);
    }



}





























