package com.example.board.controller;

import com.example.board.entity.Board;
import com.example.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
}