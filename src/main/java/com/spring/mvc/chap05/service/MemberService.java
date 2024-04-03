package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.request.LoginRequestDTO;
import com.spring.mvc.chap05.dto.request.SignUpRequestDTO;
import com.spring.mvc.chap05.entity.Member;
import com.spring.mvc.chap05.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.spring.mvc.chap05.service.LoginResult.*;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder encoder;

    // 회원 가입 처리 서비스
    public void join(SignUpRequestDTO dto) {

        // 클라이언트가 보낸 회원가입 데이터를
        // 패스워드 인코딩하여 엔터티로 변환해서 전달.
//        String encodedPw = encoder.encode(dto.getPassword());
//        dto.setPassword(encodedPw);  -> 간략하게 표현가능->dto 엔터티 매개변수로 encoder 설정
        memberMapper.save(dto.toEntity(encoder));

    }
    
    // 로그인 검증 처리
    public LoginResult authenticate(LoginRequestDTO dto) {

        Member foundMember = memberMapper.findMember(dto.getAccount());

        if (foundMember == null) {  //회원가입 안한 상태
            System.out.println(dto.getAccount() + "(은)는 없는 아이디!");
            return NO_ACC;
        }
        // 비밀번호 일치 검사
        String inputPassword = dto.getPassword(); // 사용자 입력 패스워드
        String realPassword = foundMember.getPassword(); // 실제 패스워드

        // matches(입력비번, 암호화된 비번) -> 둘이 일치하면 true, 일치하지 않으면 false
        // equals 로 비교하시면 안돼요!
        if (!encoder.matches(inputPassword, realPassword)) {
            System.out.println("비밀번호가 틀렸습니다.");
            return NO_PW;
        }

        System.out.println(dto.getAccount() + "님 로그인 성공!");
        return SUCCESS;
    }

}






















