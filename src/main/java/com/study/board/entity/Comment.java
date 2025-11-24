package com.study.board.entity;

import jakarta.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 400)
    private String content;

    @ManyToOne
    @JoinColumn(name = "board_id",nullable = false)
    private Board board;

    public Comment() {

    }

    public Comment(String content,Board board) {
        this.content = content;
        this.board = board;
    }

    public Long getId() {return id;}

    public String getContent() {return content;}
    public void setContent(String content) {this.content = content;}

    public Board getBoard() {return board;}
    public void setBoard(Board board) {this.board = board;}
}
