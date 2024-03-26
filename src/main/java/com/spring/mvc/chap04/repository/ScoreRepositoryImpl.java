package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Grade;
import com.spring.mvc.chap04.entity.Score;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository // Bean으로 등록해놔야 Service에 주입이 가능하니까
@RequiredArgsConstructor
public class ScoreRepositoryImpl implements ScoreRepository{

    //내부(중첩)클래스 문법 (밀접한 관계에 있을때 선언함)
    //내부(중첩) 클래스 (inner class)
    //두 클래스가 굉장히 긴밀한 관계가 있을 때 주로 선언.
    //해당 클래스 안에서만 사용할 클래스를 굳이 새 파일로 선언하지 않고 만들 수 있습니다.
    class ScoreMapper implements RowMapper<Score> {
        @Override
        public Score mapRow(ResultSet rs, int rowNum) throws SQLException {
            Score score = new Score(
                    rs.getString("stu_name"),
                    rs.getInt("kor"),
                    rs.getInt("eng"),
                    rs.getInt("math"),
                    rs.getInt("stu_num"),
                    rs.getInt("total"),
                    rs.getInt("average"),
                    Grade.valueOf(rs.getString("grade"))
            );
            return score;
        }
    }

    // Spring-jdbc의 핵심 객체인 JdbcTemplate의 의존성 주입 (생성자 주입)
    // 데이터베이스 접속 객체 (Connection)을 바로 활용하는 것이 가능 -> 미리 세팅 다 해놓음.
    private final JdbcTemplate jdbcTemplate;


    @Override
    public boolean save(Score score) {
        String sql = "INSERT INTO tbl_score " +
                "(stu_name, kor, eng, math, total, average, grade) " +
                "VALUES(?,?,?,?,?,?,?)";

        int result = jdbcTemplate.update(sql, score.getName(), score.getKor(), score.getEng(),
                score.getMath(), score.getTotal(), score.getAverage(),
                score.getGrade().toString());

        return result == 1 ? true : false;
    }

    @Override
    public List<Score> findAll(String sort) {
        String sql = "SELECT * FROM tbl_score";

        switch (sort) {
            case "num":
                sql += " ORDER BY stu_num";
                break;
            case "name":
                sql += " ORDER BY stu_name";
                break;
            case "avg":
                sql += " ORDER BY average DESC";
                break;
        }

        List<Score> scoreList = jdbcTemplate.query(sql, new ScoreMapper());
        return scoreList;
    }

    @Override
    public boolean delete(int stuNum) {
        String sql = "DELETE FROM tbl_score WHERE stu_num = ?";
        return jdbcTemplate.update(sql, stuNum) == 1;
    }

    @Override
    public Score findOne(int stuNum) {
        String sql = "SELECT * FROM tbl_score WHERE stu_num = ?";

        try {
            // queryForObject는 조회 결과가 없을 시 예외가 발생합니다.
            return jdbcTemplate.queryForObject(sql, new ScoreMapper(), stuNum);
        } catch (DataAccessException e) {
            // 조회결과가 없다면 catch문이 실행 -> null을 리턴.
            return null;
        }

    }
}