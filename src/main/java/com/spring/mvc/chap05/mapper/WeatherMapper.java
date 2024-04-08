package com.spring.mvc.chap05.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface WeatherMapper {

    // 매개변수 2개 설정 불가능하므로 아노테이션 이용하여 이름지정해주기(@Param)
    Map<String, Integer> getCoord(@Param("area1") String area1, @Param("area2") String area2);
}
