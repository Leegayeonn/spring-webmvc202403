package com.spring.mvc.chap05.service;

import com.mysql.cj.xdevapi.JsonParser;
import com.spring.mvc.chap05.mapper.WeatherMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherMapper mapper;

    public void getShortTermForecast(String area1, String area2) {

        // 요청보낼당시 날짜 구하기
        LocalDateTime now = LocalDateTime.now();
        String baseDate = DateTimeFormatter.ofPattern("yyyyMMdd").format(now);
        log.info("baseDate: {}", baseDate);

        Map<String, Integer> map = mapper.getCoord(area1.trim(), area2.trim());
        log.info("좌표 결과: {}", map);

        try {
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=xslbsjNPICkf%2B2T2G6bme0BihSfnUHKHH4DmLkpTwudydktk7uUrVTvyu72ZUQQzU%2FwlUAA5tFoieiHXwVKZBA%3D%3D"); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("200", "UTF-8")); /*한 페이지 결과 수*/
            urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML -> JSON 으로 고침*/
            urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8")); /*현재날짜에 맞는 일자 발표로 수정함*/
            urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0200", "UTF-8")); /*02시 발표(정시단위) */
            urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(String.valueOf(map.get("nx")), "UTF-8")); /*예보지점의 X 좌표값*/
            urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(String.valueOf(map.get("ny")), "UTF-8")); /*예보지점의 Y 좌표값*/

            log.info("완성된 URL: {}", urlBuilder.toString());


            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();
//            System.out.println(sb.toString());

            // JSON 데이터에서 차곡차곡 들어가면 최고, 최저온도 TMN, TMX 값 찾을수 있다.

            // StringBuilder 를 String 으로 변환
            String jsonString = sb.toString();

            JSONParser parser = new JSONParser();

            // String 객체를 JSON 객체로 변경해 줌.
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);

            // "response" 라는 이름의 키에 해당하는 JSON 데이터를 가져옵니다.
            JSONObject response = (JSONObject) jsonObject.get("response");

            // response 안에서 body 키에 해당하는 JSON 데이터를 가져옵니다.
            JSONObject body = (JSONObject) response.get("body");

            // body 에서 items 를 꺼내세요.
            JSONObject items = (JSONObject) body.get("items");

            // item 이라는 키를 가진 JSON 데이터를 가져올건데,
            // item 데이터는 여러값이기 때문에 배열의 문법을 제공하는 객체로 받습니다.
            JSONArray itemArray = (JSONArray) items.get("item");

            // 반복문을 이용해서 객체를 하나씩 취득한 후에 원하는 로직을 작성합니다.
            for (Object obj : itemArray) {

                // Object 를 JSON 객체로 변환
                JSONObject item = (JSONObject) obj;

                // "category" 키에 해당하는 단일 값을 가져옵니다.
                String category = (String) item.get("category");

                // "fcstValue" 키에 해당하는 단일 값을 가져옵니다.
                String fcstValue = (String) item.get("fcstValue");

                if (category.equals("TMX") || category.equals("TMN")) {
                    log.info("category: {}, value:{}", category, fcstValue);
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}














