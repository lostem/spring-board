package com.study.board;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component  //이 클래스를 스프링이 관리한느 객체(빈)으로 등록하라는 의미
public class BoardTestRunner {

    private final BoardRepository boardRepository;

    public BoardTestRunner(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Bean
    public CommandLineRunner testBoard() {
        return args -> {
            // 기존 데이터 모두 삭제
            //boardRepository.deleteAll();

            // 1. 여러건 저장
            boardRepository.save(new Board("첫글","JPA로 저장 테스트 01"));
    //        boardRepository.save(new Board("두번째 글","테스트 02"));
    //        boardRepository.save(new Board("세번째 글","테스트 03"));
    //        boardRepository.save(new Board("네번째 글","테스트 04"));

            // 1. 저장
            System.out.println("=== 게시글 목록 ===");
            for (Board b : boardRepository.findAll()) {
                System.out.println(b.getId() + " / " + b.getTitle() + " /" + b.getContent());
            }
        };
    }
}
