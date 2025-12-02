package com.study.board.api;

import com.study.board.entity.Comment;
import com.study.board.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentApiController {

    private final CommentService commentService;

    public CommentApiController(CommentService commentService) {
        this.commentService = commentService;
    }

    //1) 댓글 등록
    @PostMapping("/{boardId}")
    public Comment createComment(@PathVariable Long boardId,
                                 @RequestParam String content) {
        return commentService.createComment(boardId,content);
    }

    //2) 게시글별 댓글 목록 조회
    @GetMapping("/{boardId}")
    public List<Comment> getComments(@PathVariable Long boardId) {
        return commentService.getCommentsByBoardid(boardId);
    }

//    @GetMapping("/test/{boardId}")
//    public Comment testCreate(@PathVariable Long boardId,
//                              @RequestParam String content) {
//        return commentService.createComment(boardId, content);
//    }

    //3) 게시글별 댓글 삭제
    @DeleteMapping("/{id}")
    public void dleteComment(@PathVariable long id){
        System.out.println("댓글 삭제 ============================");
        commentService.CommentDelete(id);
    }
}
