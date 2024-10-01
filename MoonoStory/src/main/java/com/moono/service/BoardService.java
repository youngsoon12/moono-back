package com.moono.service;

import com.moono.dto.BoardDTO;
import com.moono.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardMapper boardMapper;

    public void createBoard(BoardDTO board) {
        boardMapper.insertBoard(board);
    }

    public List<BoardDTO> getAllBoards() {
        return boardMapper.getAllBoards();
    }

    public BoardDTO getBoardById(int postId) {
        return boardMapper.getBoardById(postId);
    }

    public void updateBoard(BoardDTO board) {
        boardMapper.updateBoard(board);
    }

    public void deleteBoard(int postId) {
        boardMapper.deleteBoard(postId);
    }
}