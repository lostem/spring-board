package com.study.board.repository;

import com.study.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // board_id 기준으로 필터링
    List<Comment> findByBoard_Id(Long boardId);
}
