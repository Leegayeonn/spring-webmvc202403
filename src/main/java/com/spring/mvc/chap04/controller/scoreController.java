package com.spring.mvc.chap04.controller;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
    # 컨트롤러
    - 클라이언트의 요청을 받아서 처리하고 응답을 제공하는 객체

    # 요청 URL Endpoint
    1. 학생의 성적정보 등록폼 화면을 보여주고
       동시에 지금까지 저장되어 있는 성적 정보 목록을 조회
    - /score/list   :   GET

    2. 학생의 입력된 성적정보를 데이터베이스에 저장하는 요청
    - /score/register   :  POST

    3. 성적정보를 삭제 요청
    - /score/remove    :  GET or POST

    4. 성적정보 상세 조회 요청
    - /score/detail  :   GET
 */

@Controller
@RequestMapping("/score")
@RequiredArgsConstructor  // final 이 붙은 필드를 초기화하는 생성자를 선언. (요구되는 매개 생성자)
public class scoreController {

    // DB에 데이터를 저장하기 위해 컨트롤러는 서비스가 꼭 필요하다.
    // 의존객체는 불변성을 띠게 작성하는 것이 좋다. -> @RequiredArgsConstructor 로 초기화 진행
    private final ScoreService service;

    /*
    - FM 방식으로 처리 -> 롬복으로 대체. 생성자가 단 하나라면 autowired 를 생략할 수 있음.
    @Autowired  // -> 컨테이너에 스코어서비스 객체를 자동으로 주입시킴
    public scoreController(ScoreService service) {
        this.service = service;
    }
     */

    // 1. 성적 입력폼 띄우기 + 목록조회
    @GetMapping("/list")
    public String list(){
        return "chap04/score-list";
    }

    // 2. 성적정보를 데이터베이스에 저장하는요청
    @PostMapping("/register")
    public String register(ScoreRequestDTO dto) {
        System.out.println("/score/register: POST!!!");
        System.out.println("dto = " + dto);

        service.insertScore(dto);
        return null;
    }



}







