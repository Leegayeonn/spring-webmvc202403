package com.spring.mvc.chap04.entity;


import lombok.*;

@Setter @Getter @ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private int id;
    private String personName;
    private int personAge;

}
