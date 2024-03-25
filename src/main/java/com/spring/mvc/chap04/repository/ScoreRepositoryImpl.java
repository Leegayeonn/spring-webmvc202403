package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Score;
import org.springframework.stereotype.Repository;

@Repository // Bean 으로 등록해놔야 Service 에  주입이 가능하니까.
public class ScoreRepositoryImpl implements ScoreRepository{
    @Override
    public boolean save(Score score) {
        return false;
    }
}
