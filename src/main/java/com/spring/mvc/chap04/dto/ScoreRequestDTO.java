package com.spring.mvc.chap04.dto;

import lombok.*;

/**
 * 서버가 클라이언트에 데이터를 전달할 때, 클라이언트에서 넘겨진 데이터를 DB로 전달할 때
 * 데이터베이스에 있는 모든 데이터를 전달하면
 * 민감한 정보나 보안상의 정보가 같이 전달될 가능성이 있다.
 * 그래서 클라이언트에 보여줄 데이터만 선별해서 응답하도록,
 * 클라이언트 쪽에서 요청과 함께 보내준 데아터만 정리해서 넘길 수 있도록
 * DTO 클래스를 사용한다.
 */

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ScoreRequestDTO {

    // form 에서 넘어오는 데이터만 깔끔하게 받아 객체화
    // DTO 클래스만 봐도 어떤 데이터가 클라이언트로부터 전달되는지 명확화.

    private String name;
    private int kor, eng, math;


}
