package com.spring.mvc.chap04.service;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.dto.ScoreResponseDTO;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap04.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 컨트롤러와 레파지토리 사이에 위치하여
 * 중간로직을 처리하는 역할
 *
 * 컨트롤러 <-> 서비스 <-> 레파지토리
 */

@Service
@RequiredArgsConstructor
public class ScoreService {

    // 서비스는 레파지토리 계층과 의존관계가 있으므로 객체가 생성될때 자동 주입 세팅.
    private final ScoreRepository repository;


    // 성적 입력 중간처리
    // 컨트롤러가 dto 를 넘김 -> 서비스쪽에서는 값을 정제하고 entity 로 변환 -> 레파지토리 계층에 넘기자.
    public boolean insertScore(ScoreRequestDTO dto) {
        Score score = new Score(dto);
        return repository.save(score);
    }


    // 목록 조회 중간처리
    /*
        컨트롤러는 데이터베이스에서 성적정보 리스트를
        조회해 오기를 원하고 있다.
        그런데 데이터베이스는 민감한정보까지 모두 조회한다.
        그리고 컬럼명도 그대로 노출하기 때문에
        이 모든것을 숨기는 처리를 하고 싶다. -> response용 DTO를 생성하자!
     */
    public List<ScoreResponseDTO> findAll(String sort) {
        List<ScoreResponseDTO>dtoList = new ArrayList<>();

        List<Score> scoreList = repository.findAll(sort);
        for (Score score : scoreList) {
            ScoreResponseDTO dto = new ScoreResponseDTO(score);
            dtoList.add(dto);
        }

        return dtoList;
    }

    public void remove(int stuNum) {
        repository.delete(stuNum);
    }

    public Score findOne(int stuNum) {
        return repository.findOne(stuNum);
    }
}


















