package com.moono.controller;

import com.moono.dto.BoardDTO;
import com.moono.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") 
@RestController
@RequestMapping("/api/cheerup")
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 게시글 목록 조회
    @GetMapping
    public List<BoardDTO> getAllBoards() {
        return boardService.getAllBoards();
    }

    // 게시글 상세 조회
    @GetMapping("/{postId}")
    public BoardDTO getBoardById(@PathVariable int postId) {
        return boardService.getBoardById(postId);
    }

    // 게시글 작성
    @PostMapping
    public void createBoard(@RequestBody BoardDTO board) {
        boardService.createBoard(board);
    }

    // 게시글 수정
    @PutMapping("/{postId}")
    public void updateBoard(@PathVariable int postId, @RequestBody BoardDTO board) {
        board.setPostId(postId);
        boardService.updateBoard(board);
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public void deleteBoard(@PathVariable int postId) {
        boardService.deleteBoard(postId);
    }
}