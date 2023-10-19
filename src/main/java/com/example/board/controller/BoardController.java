package com.example.board.controller;

import com.example.board.entity.Board;
import com.example.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping(path = "/write")
    public String write(@RequestBody Board board) {

        boardService.write(board);

        return "작성 성공";
    }

    @GetMapping(path = "/list")
    public Page<Board> getList(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Board> boardList = boardService.boardList(pageable);

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

    @DeleteMapping(path = "/delete/{id}")
    public String Delete(@PathVariable int id) {

        boardService.boardDelete(id);

        return "삭제 성공";
    }

}