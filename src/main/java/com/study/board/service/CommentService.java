package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.entity.Comment;
import com.study.board.repository.BoardRepository;
import com.study.board.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public CommentService(CommentRepository commentRepository, BoardRepository boardRepository){
        this.commentRepository = commentRepository;
        this.boardRepository = boardRepository;
    }

    // 1) 댓글 저장
    @Transactional
    public Comment createComment(Long boardId, String content) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다 id=" + boardId));

        Comment comment = new Comment(content, board);
        return  commentRepository.save(comment);
    }

    // 2) 게시글별 댓글 목록 조회
    @Transactional(readOnly = true)
     public List<Comment> getCommentsByBoardid(Long boardid) {
        return commentRepository.findAll();
    }
}
