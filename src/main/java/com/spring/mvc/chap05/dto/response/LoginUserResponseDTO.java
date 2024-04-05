package com.spring.mvc.chap05.dto.response;


import jdk.jfr.StackTrace;
import lombok.*;

@Getter @StackTrace @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginUserResponseDTO {

    private String account;
    private String name;
    private String email;
    private String auth;
    private String profile;


}
