package com.example.board.controller;

import com.example.board.entity.Board;
import com.example.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping(path = "/write")
    public String write(@RequestBody Board board) {

        boardService.write(board);

        return "성공";
    }

    @GetMapping(path = "/getList")
    public List<Board> getList() {

        List<Board> boardList = boardService.boardList();
        return boardList;
    }

    @GetMapping("/view/{id}") // 게시글 상세 페이지
    public Board view(@PathVariable Integer id) {

        Board board = boardService.boardView(id);

        return board;
    }

    @PutMapping(path="/update/{id}")  // 게시판 글 수정
    public String Update(@RequestBody Board board, @PathVariable Integer id){

        Board boardTmp = boardService.boardView(id);
        boardTmp.setTitle(board.getTitle());
        boardTmp.setContent(board.getContent());
        boardTmp.setDate(board.getDate());

        boardService.write(boardTmp);

        return "수정 성공";
    }
}