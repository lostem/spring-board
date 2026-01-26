package com.study.board.entity;

import jakarta.persistence.*;

@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    //기본 생성자 (JPA가 내부에서 객체를 만들때 필수로 요구하는 생성자)
    public Board() {}

    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // getter/setter
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
