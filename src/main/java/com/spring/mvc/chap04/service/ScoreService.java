package com.spring.mvc.chap04.service;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap04.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        repository.save(score);

        return false;
    }


}
