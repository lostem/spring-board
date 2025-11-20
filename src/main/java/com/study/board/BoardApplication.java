package com.study.board;


import com.study.board.entity.Member;
import com.study.board.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BoardApplication {

    private final MemberRepository memberRepository;

    //생성자 주입
    public BoardApplication(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(BoardApplication .class,args);
    }

//애플리케이션 시작 시 한번 실행 되는 테스트 코드
@Bean
public CommandLineRunner testRunner() {
    return args -> {
        //1.멤버 저장
        Member m1 = new Member("홍길동");
        Member m2 = new Member("이몽룡");

        //2. 전체 조회
        System.out.println("=== 저장된 멤버 목록 ===");
        for (Member m : memberRepository.findAll()) {
            System.out.println(m.getId() + " / " + m.getName());
            }
        };
    }
}
