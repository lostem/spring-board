package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    private final BoardRepository boardRepository;

    public BoardController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    //게시글 목록 조회
    @GetMapping("/boards")
    public String list(Model model) {
        model.addAttribute("boards", boardRepository.findAll());
        return "board/list";
    }

    @GetMapping("/boards/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음:" + id));
        model.addAttribute("board", board);
        return "board/detail";  //templates/board/detail.html
    }

    @GetMapping("/boards/new")
    public String creatForm(Model model) {
        model.addAttribute("board", new Board());
        return "board/form";    //templates/board/form.html
    }

    @PostMapping("/boards")
    public String ceate(@ModelAttribute Board board) {
        boardRepository.save(board);
        return "redirect:/boards";
    }
}
