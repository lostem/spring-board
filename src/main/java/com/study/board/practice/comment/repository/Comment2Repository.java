package com.study.board.practice.comment.repository;

import com.study.board.practice.comment.entity.Comment2;
import org.springframework.data.jpa.repository.JpaRepository;

//인터페이스 선언하고 JPArepository 는 <엔터티타입 , PK타입> 형식
//<엔터티타입, PK타입> 첫번째 제네릭에 Comment2는 이레포지토리가 어떤 엔터티를 다룰지 판단
//<엔터티타입, PK타입> 두번째 제너릭에 PK타입이 Long타입으로 들어감
//이렇게 적는순간 Comment2 save2(Comment2 entity) , List<Comment2> findAll()
//Optional<Comment2>findAll() , void deleteById(Long id)
//인터페이스 extens JpaRepository 만 해두면 스프링이 런타임에 구현체를 만들어서 빈으로 등록해줌
// 그래서 @Repository 따로 안붙여도 잘 동작 하는게 스프링 데이터 JPA 패턴
public interface Comment2Repository extends JpaRepository<Comment2, Long> {
}
