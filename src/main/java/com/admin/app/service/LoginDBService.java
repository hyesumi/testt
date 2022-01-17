package com.admin.app.service;

import com.admin.app.dto.Member;
import com.admin.app.dto.MemberRepository;
import com.admin.app.mapper.LoginMapper;
import com.admin.app.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
public class LoginDBService {

    //private final MemberRepository memberRepository;

    LoginMapper loginMapper;

    public LoginDBService(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }

    public Member login(String loginId, String password){
        System.out.println("ID="+loginId);
        Member member = loginMapper.findByLoginId(loginId);
//       return memberMapper.findByLoginId(loginId)
//                .filter(m -> m.getPassword().equals(password))
//                .orElse(null);
        return member;
    }

}
