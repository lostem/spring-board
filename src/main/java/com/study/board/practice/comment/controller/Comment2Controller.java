package com.study.board.practice.comment.controller;

import com.study.board.practice.comment.entity.Comment2;
import com.study.board.practice.comment.service.Comment2Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Comment2Controller {
    private final Comment2Service comment2Service;

    public Comment2Controller(Comment2Service comment2Service) {
        //컨트롤러는 Comment2Service 객체를 컨트롤러 안에 주입하기 위해 생성자를 만듬
        //컨트롤러 자체로는 아무것도 할수가 없고 비즈니스 로직은 서비스가 담당한다
        //받은 서비스를 필드에 넣어둬야 컨트롤러에서 서비스 메서드를 호출해 사용할수 있다
        this.comment2Service = comment2Service;
    }

    @PostMapping("/comments")
    //comments 주소로 들어오는 POST 요청을 이 메서드가 처리하겠다는 뜻
    public String saveComment(@RequestParam String content) {
        //@RequestParam String content은 HTTP 요청 파라미터 중에서 content= 넘어온 값을 받아서 값을
        //메서드 파라미에 content에 넣어준다는 뜻 즉 폼에서 <input name="content"...> 보내면 여기로 들어옴
        Comment2 save = comment2Service.commentSave(content);
        //서비스에 위임해서 DB에 저장
        return "redirect:/";
        //어디로 다시 보내줄지 설정
    }

    @PostMapping("/comments/{id}/update")
    public String updateComment(@PathVariable Long id, @RequestParam String content) {
        Comment2 update = comment2Service.commentUpdate(id, content);
        return "redirect:/";
        //redirect 는  서버가 브라우저에게 / 지정한 주소로 다시 요청 보내라고 지시하는 것 즉 / 첫 브라우저 화면이면 그 화면으로 이동시킴
    }

    @PostMapping("/comments/{id}/delete")
    public String deleteComment(@PathVariable Long id) {
        //리턴 타입이 String 인것은 댓글 삭제후 사용자를 어느 화면으로 보내줄지 표현한 것
        comment2Service.deleteComment(id);
        return "redirect:/";
    }
}
