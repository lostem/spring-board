package com.study.board.auth;

import com.study.board.entity.Member;
import com.study.board.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Member> login(String username, String rawPassword) {
        return memberRepository.findByUsername(username).filter(m -> passwordEncoder.matches(rawPassword, m.getPassword()));
    }
}
