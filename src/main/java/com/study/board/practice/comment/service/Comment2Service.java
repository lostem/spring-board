package com.study.board.practice.comment.service;

import com.study.board.practice.comment.entity.Comment2;
import com.study.board.practice.comment.repository.Comment2Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@Service 하는 이유는 컨테이너가 이 클래스를 서비스 빈으로 인식해야함
@Service
public class Comment2Service {
    //댓글 레포지토리 의존성을 담는 필드 final 사용 이유는 한번 주입하면 변경하지 않겠다는 뜻
    private final Comment2Repository comment2Repository;
    
    //메서드 이름이 클래스 이름이랑 같고 리턴 타입이 없음 -> 생성자 문법
    //
    public Comment2Service(Comment2Repository comment2Repository) {
        //밖에서 Comment2Repository 객체를 하나 받와와서 이 서비스 안에서 사용하겠다는 의미
        //this.comment2Repository 은 final선언한 필드를 가르킴
        //생성자 파라미터로 받은 리포지토리 객체를 이 서비스의 필드에 저장해 둔다 = 필드 초기화
        //그래서 이 한줄로 Comment2Repository 객체(파라미터)를 서비스 내부 필드 comment2Repository
        //에 꽂아 넣어서 나중에 서비스 메서드에서 comment2Repository.save(..)가 가능하게 함
        this.comment2Repository = comment2Repository;
    }
    @Transactional
    public Comment2 commentSave(String content){
        Comment2 comment = new Comment2(content);
        //Comment2 엔터티 구조를 사용해서 comment만 채운 Comment2 객체(인스턴스)하나를 메모리에 만듬
        comment2Repository.save(comment);
        return comment;
    }

    @Transactional
    public Comment2 commentUpdate(Long id, String content){
        Comment2 comment = comment2Repository.findById(id)
                //기존 댓글을 id로 찾음
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다 id=" + id));
                //orElse(기본값)-> 값이 없으면 기본값을 돌려줌 , orElseThrow(예외생성함수)-> 값이 없으면 “이 예외를 던져라”
        comment.setContent(content);
        //가져온 엔터티의 내용을 바꿈(수정함)
        return comment;
        //수정된 객체를 리턴함
    }

    @Transactional
    public void deleteComment(Long id){
        comment2Repository.deleteById(id);
    }


}
