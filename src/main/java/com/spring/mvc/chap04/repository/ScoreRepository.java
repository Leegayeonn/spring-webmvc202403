package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Person;
import com.spring.mvc.chap04.entity.Score;
import jdk.jfr.Percentage;

/**
 * 성적정보를 잘 저장하고 조회하고 수정하고 삭제하는 역할
 * 데이터베이스와 같은 저장소에 접근하는 객체 (Data Access Object : DAO)
 *
 * 왜 인터페이스로 추상화하는가??
 * 실제로 저장소라는 개념은 구체적이지 않다!!
 * 파일로 저장, 인메모리 저장, 관계형 데이터베이스, NOSQL...
 */

public interface ScoreRepository {


    // 성적 정보 등록
    boolean save(Score score);
}

