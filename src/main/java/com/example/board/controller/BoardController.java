package com.example.board.controller;

import com.example.board.entity.Board;
import com.example.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping(path = "/write")
    public String write(@RequestBody Board board){

        boardService.write(board);

        return "성공";
    }

    @GetMapping(path ="/getList")
    public List<Board> getList() {

        List<Board> boardList = boardService.boardList();
        return boardList;
    }
}
