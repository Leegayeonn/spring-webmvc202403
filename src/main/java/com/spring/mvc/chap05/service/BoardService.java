package com.spring.mvc.chap05.service;


import com.spring.mvc.chap05.dto.request.BoardWriterRequestDTO;
import com.spring.mvc.chap05.dto.response.BoardListResponseDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service // 연관관계를 위하여
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository repository;


    public void write(BoardWriterRequestDTO dto) {
        Board board = new Board(dto);  // dto를 엔터티로 변환
        repository.save(board);
    }

    public void delete(int boardNo) {
        repository.delete(boardNo);
    }

    public void findOne(int boardNo) {
        repository.findOne(boardNo);
    }


    // repository 로 부터 전달받은 entity List 를 DTO List 로 변환하여 컨트롤러에게 리턴.
    public List<BoardListResponseDTO> getList() {
        List<BoardListResponseDTO> dtoList = new ArrayList<>();
        List<Board> boardList = repository.findAll();
        for (Board board : boardList) {
            BoardListResponseDTO dto = new BoardListResponseDTO(board);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
