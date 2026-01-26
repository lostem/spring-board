package com.study.board.auth;

import com.study.board.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final AuthService authService;

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginForm",new LoginForm());
        return "board/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginForm form, HttpSession session, Model model) {
        return authService.login(form.getUsername(), form.getPassword())
                .map(member -> {
                    session.setAttribute(SessionConst.LOGIN_MEMBER_ID, member.getId());
                    return "redirect:/";
                })
                .orElseGet(() -> {
                    model.addAttribute("error", "로그인 실패");
                    return "board/login";
                });
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @PostConstruct
    public void init() {
        // "testuser"라는 회원이 있으면 꺼내옵니다.
        memberRepository.findByUsername("testuser").ifPresent(member -> {
            // 비밀번호를 암호화된 '1234'로 바꿔서 다시 저장합니다.
            member.setPassword(passwordEncoder.encode("1234"));
            memberRepository.save(member);

            System.out.println("=========================================");
            System.out.println("testuser 비밀번호 암호화 완료! (PW: 1234)");
            System.out.println("=========================================");
        });
    }
}
