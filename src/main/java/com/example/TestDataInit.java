package com.example;

import com.example.member.Member;
import com.example.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;
    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {

        Member member = new Member();
        member.setLoginId("test");
        member.setPassword("test!");
        member.setName("테스터");

        Member member2 = new Member();
        member2.setLoginId("test2");
        member2.setPassword("test!");
        member2.setName("테스터2");

        memberRepository.save(member);
        memberRepository.save(member2);
    }

}