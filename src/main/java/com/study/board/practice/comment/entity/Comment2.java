package com.study.board.practice.comment.entity;

import jakarta.persistence.*;

@Entity
public class Comment2 {

    @Id
    //PK 값을 어떻게 할지에 대한 전략(strategy)
    //생성전략(GenerationType)은 ID(IDENTITY) 이 방식은 AUTO_INCREMENT
    //자동으로 번호가 증가한다고 보면됨
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //nullable = false ---> NOT NULL
    //private 쓰는 이유는 나중에 Getter / Setter 로 접근하는게 일반적인 엔터티 패턴임
    @Column(nullable = false)
    private String content;

    //JPA 사용하기 위해서 항상 기본 생성자 필요하며
    //기본 생성자가 있기에 JPA는 비어있는 객체를 하나 만들고 그다음 Id , Contetn 필드에 값을 채워넣음
    //기본 생성자가 없으면 newInstance()를 못해 에러가 남
    //프로텍트는 JPA 같은 패키지/상속 관계에서만 쓰기위해 선언한거
    protected Comment2() {}

    //편의를 위한 생성자
    //public Comment2는 서비스, 컨틀롤러 등 다른 패키지에서도 호출 가능하게 열어둠
    //(String conetne) 생성자 호출할때 반드시 문자열 한개를 넘겨야 함
    //문자열 하나를 받아서 그걸 content 필드에 넣은 상태로 Comment2 객체를 만들어 주는 생성자
    public Comment2(String content) {
        //오른쪽에 문자열 넘긴 값이 들어오고 이 객체의 content 필드에 집어넣음
        this.content = content;
    }

    //Getter
    //각 필드에 접근 할수 있는 getter 메서드
    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    //setter
    //댓글을 바꾸고 싶을때 쓰는 setter
    public void setContent(String content) {
        this.content = content;
    }
}
