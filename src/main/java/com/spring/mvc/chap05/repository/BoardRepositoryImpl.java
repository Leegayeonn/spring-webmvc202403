package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap04.entity.Grade;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap05.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository  // 서비스가 꼭 필요로 하고 자동 주입
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepository {
    
    // 생성자 주입을 위한 객체 생성
    private final JdbcTemplate template;

    class BoardMapper implements RowMapper<Board> {
        @Override
        public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
            Board board = new Board(
                    rs.getInt("board_no"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getInt("view_count"),
                    rs.getTimestamp("reg_date").toLocalDateTime(),
                    rs.getString("writer")
            );
            return board;
        }
    }


    @Override
    public List<Board> findAll() {
        String sql = "SELECT * FROM tbl_board ORDER BY board_no DESC";

        List<Board> result = template.query(sql, new BoardMapper());

        return result;
    }

    @Override
    public Board findOne(int boardNo) {
        String sql = "SELECT * FROM tbl_board WHERE board_no = ?";

        try {
            return template.queryForObject(sql, new BoardMapper(), boardNo);
        } catch (DataAccessException e) {
            return null;
        }
    }

    /*
    private int boardNo; // 게시글 번호
    private String title; // 글제목
    private String content; // 내용
    private int viewCount; // 조회수
    private LocalDateTime regDate; // 작성일자시간
    private String writer; // 작성자
     */
    @Override
    public void save(Board board) {
        System.out.println("kekekek");
        String sql = "INSERT INTO tbl_board (title,content,writer) VALUES(?,?,?);";

        template.update(sql, board.getTitle(), board.getContent(), board.getWriter());
    }

    @Override
    public void delete(int boardNo) {
        String sql = "DELETE FROM tbl_board WHERE board_no = ?";

        template.update(sql, boardNo);

    }

    @Override
    public void updateViewCount(int bno) {
        String sql = "UPDATE tbl_board SET view_count = view_count + 1 WHERE board_no = ?";

        template.update(sql, bno);
    }
}
