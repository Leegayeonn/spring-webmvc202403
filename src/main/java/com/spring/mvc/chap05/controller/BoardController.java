package com.spring.mvc.chap05.controller;


import com.spring.mvc.chap05.common.Page;
import com.spring.mvc.chap05.common.PageMaker;
import com.spring.mvc.chap05.dto.request.BoardWriterRequestDTO;
import com.spring.mvc.chap05.dto.response.BoardDetailResponseDTO;
import com.spring.mvc.chap05.dto.response.BoardListResponseDTO;
import com.spring.mvc.chap05.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")  // 공통 URL
public class BoardController {

    private final BoardService service;

    // 1. 목록 조회 요청 (/board/list : GET)
    // chap05/list.jsp
    @GetMapping("/list")
    public String list(Model model, Page page) {
        System.out.println("page = " + page);
        List<BoardListResponseDTO> dtoList = service.getList(page);

        // 페이징 버튼 알고리즘 적용 -> 사용자가 요청한 페이지 정보, 총 게시물 개수를 전달.
        // 페이징 알고리즘 자동 호출.
        PageMaker maker = new PageMaker(page, service.getCount());

        // model 에 글 목록 뿐만 아니라 페이지 버튼 정보도 같이 담아서 전달하자.
        model.addAttribute("bList", dtoList);
        model.addAttribute("maker", maker);
        return "chap05/list";
    }

    // 2. 글쓰기 화면요청 (/board/write : GET)
    // chap05/write.jsp 로 이동
    @GetMapping("/write")
    public String write() {
        System.out.println("/board/writer : GET!!");
        return "chap05/write";
    }

    // 3. 글쓰기 등록요청 (/board/write : POST)
    // BoardWriterRequestDTO 를 활용하여 파라미터 처리 -> dto.request 패키지에 생성
    // 등록 완료 후에는 목록 조회 요청이 다시 들어오게끔 처리.
    @PostMapping("/write")
    public String write(BoardWriterRequestDTO dto) {
        System.out.println("/board/write : POST!!");

        service.write(dto);

        return "redirect:/board/list";
    }
    
    // 4. 글 삭제 요청 (/board/delete : GET)
    // 글번호 전달되는 삭제 진행
    @GetMapping("/delete")
    public String delete(int bno) {
        System.out.println("/board/delete : GET!!" + bno);

        service.delete(bno);

        return "redirect:/board/list";
    }

    // 5. 글 상세보기 요청 (/board/detail : GET)
    // 글 번호 전달되면 해당 내용 상세보기 처리
    // chap05/detail.jsp
    @GetMapping("/detail/{bno}")
    public String detail(@PathVariable("bno") int bno,Model model) {
        System.out.println("/board/detail : GET!!" + bno);

        BoardDetailResponseDTO dto = service.detail(bno);

        model.addAttribute("b", dto);

        return "chap05/detail";
    }



}


























