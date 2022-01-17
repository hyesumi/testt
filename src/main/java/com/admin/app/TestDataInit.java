package com.admin.app;

import com.admin.app.dto.Member;
import com.admin.app.dto.MemberRepository;
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
        member.setRole("SUPER_ADMIN");

        Member member2 = new Member();
        member2.setLoginId("test2");
        member2.setPassword("test!");
        member2.setName("테스터2");
        member2.setRole("ADMIN");

        memberRepository.save(member);
        memberRepository.save(member2);
    }

}