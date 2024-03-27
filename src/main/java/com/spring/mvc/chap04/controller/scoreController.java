package com.spring.mvc.chap04.controller;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.dto.ScoreResponseDTO;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap04.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
    public String list(Model model,
                       @RequestParam(defaultValue = "num") String sort) {
        System.out.println("/score/list: GET!!");
        List<ScoreResponseDTO> dtoList = service.findAll(sort);

        model.addAttribute("sList", dtoList);
        return "chap04/score-list";  //forward
    }

    // 2. 성적정보를 데이터베이스에 저장하는요청
    @PostMapping("/register")
    public String register(ScoreRequestDTO dto) {
        System.out.println("/score/register: POST!!!");
        System.out.println("dto = " + dto);

        service.insertScore(dto);

        // 등록이 완료가 되었다면 목록 화면으로 데이터를 전달해서 목록화면을 보여주고 싶다.

        /*
            # forward vs redirect
            - 포워드는 요청 리소스를 그대로 전달해줌.
            - 따라서 URL이 변경되지 않고 한번의요청과 한번의 응답만 이뤄짐
            - forward 할 때는 포워딩할 파일의 경로를 적습니다. (/views/chap04/score-list.jsp)

            - 리다이렉트는 요청후에 자동응답이 나가고
              2번째 자동요청이 들어오면서 2번째 응답을 내보냄
            - 따라서 2번째 요청의 URL로 자동 변경됨
            - redirect 할 때는 다시 들어왔을면 하는 요청 url을 적는 것 (/score/list -> 목록 요청)
         */

        return "redirect:/score/list";
    }

    // 성적 삭제 요청
    @PostMapping("/remove")
    public String remove(int stuNum) {
        System.out.println("/score/remove: POST!");
        System.out.println("stuNum = " + stuNum);

        service.remove(stuNum);

        return "redirect:/score/list";
    }

    // 성적 상세 조회 요청
    @GetMapping("/detail")
    public String detail(int stuNum, Model model) {
        System.out.println("/score/detailL GET!!");
        System.out.println("stuNum = " + stuNum);

    retrieve(stuNum, model);
        // 상세보기이기 때문에 DTO 가 아닌 Entity를 담아서 jsp로 보냅니다.
        // chap04/score-detail.jsp
    return "chap04/score-detail";
    }

    // 수정 페이지로 이동 요청
    @GetMapping("/modify")
    public String modify(int stuNum, Model model) {
        System.out.println("/score/modify : GET!!");
        System.out.println("stuNum = " + stuNum);

        retrieve(stuNum, model);

        return "chap04/score-modify";

    }

    // 수정 처리 요청
    @PostMapping("/modify")
    public String modify(ScoreRequestDTO dto, int stuNum) { //커맨드 객체(값을 모두다 받을필요가없음, name 안써도됨)
        System.out.println("/score/modify :POST!!!");
        System.out.println("stuNum = " + stuNum);

        // 서비스, 레파지토리 계층과 연계하여 update 처리를 진행해주세요.
        // 수정이 완료된 후 사용자에게 응답할 페이지는
        // 최신 수정 내용이 반영된 detail 페이지 입니다. -> redirect
        service.update(dto, stuNum);

        return "redirect:/score/detail?stuNum=" + stuNum;
    }



    private void retrieve(int stuNum, Model model) {
        Score score = service.findOne(stuNum);
        model.addAttribute("s", score);
    }


}







