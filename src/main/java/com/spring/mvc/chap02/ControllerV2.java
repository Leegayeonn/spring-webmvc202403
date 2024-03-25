package com.spring.mvc.chap02;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/model") // 공통 url 맵핑 -> 메서드마다 /model 을 작성할 필요없음
public class ControllerV2 {

        /*
        /model/hobbies : GET
        -> hobbies.jsp파일로 사용자 이름정보와 취미목록을 뿌려주고 싶다.


        == 1. Model 객체 사용
        -> 자바가 갖고 있는 데이터를 JSP로 넘겨줄 때 사용하는 바구니같은 역할
     */

    @GetMapping("/hobbies")
    public String hobbies(Model model) {
        System.out.println("/model/hobbies: GET 요청!");

        String name = "군범이";
        List<String> hobbyList = new ArrayList<>();
        Collections.addAll(hobbyList, "노래부르기", "춤추기", "쨱쨱거리기", "멍때리기");

        // Model 객체에 데이터 담기 model.addAttribute("이름", 값)
        model.addAttribute("userName", name);
        model.addAttribute("hobbies", hobbyList);


        return "chap02/hobbies";
    }

    // == 2. ModelAndView 객체 사용
    @GetMapping("/hobbies2")
    public ModelAndView hobbies2(){
        System.out.println("/model/hobbies2: GET 요청!");

        // jsp 로 보낼 데이터 생성
        String name = " 사범이";
        // List.of 를 통해 빠른 리스트 생성이 가능하지만, 해당리스트는 변경, 삽입, 수정에 제한(불변리스트!)
        List<String> hobbyList = List.of("낮잠자기", "춤추기", "운동하기");

        // jsp 로 보낼 데이터를 ModelAndView 에 담기
        ModelAndView mv = new ModelAndView();
        mv.addObject("userName", name);
        mv.addObject("hobbies", hobbyList);

        // view 의 데이터를 따로 담아줌
        mv.setViewName("chap02/hobbies");

        return mv; //문자열이 아닌 mv 객체를 리턴.
    }





}











