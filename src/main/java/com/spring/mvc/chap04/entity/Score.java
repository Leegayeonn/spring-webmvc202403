package com.spring.mvc.chap04.entity;


// DB 와 1:1 로 매치되는 클래스다!

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import lombok.*;

/**
 * 엔터티 클래스
 * - 데이터베이스에 저장할 데이터를 자바 클래스에 매칭
 *

 -- 성적 테이블 생성하기
 create table tbl_score (
     stu_num INT(10) PRIMARY KEY AUTO_INCREMENT,
     stu_name VARCHAR(255) NOT NULL,
     kor INT(3) NOT NULL,
     eng INT(3) NOT NULL,
     math INT(3) NOT NULL,
     total INT(3),
     average FLOAT(5, 2),
     grade CHAR(1)
 );
 -- INT 정수, FLOAT 실수

 SELECT * FROM tbl_score;

 *
 */

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

public class Score {

    private String name; // 학생이름
    private int kor, eng, math; // 국영수 점수

    // 가공해야할 값
    private int stuNum; // 학번
    private  int total; // 총점
    private double average; // 평균
    private Grade grade; // 학점

    // 객체가 생성될 때 dto 를 전달받아서 자신의 메서드로 모든 필드를 초기화하는 생성자를 선언.
    public Score(ScoreRequestDTO dto) {
        convertInputData(dto); // 입력받은 값으로 필드 초기화
        calculateTotalAndAverage(); // 총점과 평균계산
        makeGrade(); // 평균에 따른 학점 -> 모든필드 세팅완료 -> 레파지토리에게 전달할 준비 완료.
    }
    
    // 총점, 평균 계산 로직
    private void calculateTotalAndAverage() {
        this.total = kor + eng + math;
        this.average = total / 3.0;  // 더블 연산이기 떄문에 3.0 으로 나눔
    }

    // 평균에 따른 학점  부여
    private void makeGrade() {
        if (average >= 90) this.grade = Grade.A;
        else if (average >= 80) this.grade = Grade.B;
        else if (average >= 70) this.grade = Grade.C;
        else if (average >= 60) this.grade = Grade.D;
        else this.grade = Grade.F;
    }

    // 전달되는 dto 에서 필요한 데이터를 Score 의 필드에 할당하는 메서드
    private void convertInputData(ScoreRequestDTO dto){
        this.name = dto.getName();
        this.kor = dto.getKor();
        this.eng = dto.getEng();
        this.math = dto.getMath();
    }
    







    
    
    
    
    
    
    
    
    
    
    
    
}














