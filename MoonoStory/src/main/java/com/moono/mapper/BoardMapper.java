package com.moono.mapper;

import com.moono.dto.BoardDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {
    
    // 게시글 작성
    @Insert("INSERT INTO board (userId, title, content) VALUES (#{userId}, #{title}, #{content})")
    void insertBoard(BoardDTO board);

    // 게시글 목록 조회
    @Select("SELECT * FROM board")
    List<BoardDTO> getAllBoards();

    // 게시글 상세 조회
    @Select("SELECT * FROM board WHERE postId = #{postId}")
    BoardDTO getBoardById(int postId);

    // 게시글 수정
    @Update("UPDATE board SET title = #{title}, content = #{content} WHERE postId = #{postId}")
    void updateBoard(BoardDTO board);

    // 게시글 삭제
    @Delete("DELETE FROM board WHERE postId = #{postId}")
    void deleteBoard(int postId);
}