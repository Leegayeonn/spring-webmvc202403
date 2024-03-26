package com.spring.mvc.chap04.jdbc;

import com.spring.mvc.chap04.entity.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class jdbcRepositoryTest {

    @Autowired
    jdbcRepository repository;

    @Test
    @DisplayName("Person 객체 정보를 데이터베이스에 정상 삽입해야한다.")
    void saveTest(){
        Person p = new Person(1, "이가연", 27);

        repository.save(p);
    }

    @Test
    @DisplayName("더비 데이터 10개를 삽입해야한다.")
    void vulkInsert(){
        for (int i = 0; i < 10; i++) {
            Person p = new Person(0, "군범이" + i, i+10);
            repository.save(p);
        }
    }

    @Test
    @DisplayName("전체 회원을 조회하면 회원리스트의 사이즈는 12일 것이다.")
    void findAllTest() {
        List<Person> people = repository.findAll();

        for (Person person : people) {
            System.out.println(person);
        }

        // 나는 people.size()가 12와 같은 것임을 단언하겠다.
        Assertions.assertEquals(12, people.size());
        assertNotNull(people);
    }

    @Test
    @DisplayName("특정 아이디 회원을 조회하면 하나의 회원만 조회될 것이고, " +
            "없는 id를 전달하면 null 이 리턴될 것이다.")
    void findOneTest(){
        int id = 600;

        Person p = repository.findOne(id);
        System.out.println(p);

        assertNull(p);

    }








    @Test
    @DisplayName("회원번호가 2번인 회원의 이름과 나이를 수정해야 한다.") // 목표작성
    void updateTest() {
        // given: 테스트를 위해 데이터를 세팅
        int id = 2;
        String newName = "지렁이";
        int newAge = 29;
        Person p = new Person(id, newName, newAge);

        // when: 테스트 목표를 확인하여 실제 테스트가 진행되는 구간
        repository.update(p);

        // then: 테스트 결과 확인하는 구간 (단언 기법 -> Assertion)

    }

    @Test
    @DisplayName("회원번호가 1인 회원을 삭제해야 한다.")
    void deleteTest() {
        int id = 1;

        repository.delete(id);
    }


}

















