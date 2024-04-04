package com.spring.mvc.config;

import com.spring.mvc.interceptor.AfterLoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 내가 만든 인터셉터들을 스프링 컨텍스트에 등록하는 설정 파일
@Configuration  // 설정용 빈등록
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private final AfterLoginInterceptor afterLoginInterceptor;


    // 메서드 재정의에서 꺼낸거임!
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        
        // 로그인 후 비회원전용 페이지 접근 차단 인터셉터 등록
        registry
                .addInterceptor(afterLoginInterceptor) // 어떤 인터셉터를 등록할 것인가
                .addPathPatterns("/members/sign-up", "/members/sign-in"); // 어떤 요청에서 동작하게 할것인가
    }
}
