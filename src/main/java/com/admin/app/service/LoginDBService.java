package com.admin.app.service;

import com.admin.app.dto.Member;
import com.admin.app.dto.MemberRepository;
import com.admin.app.mapper.LoginMapper;
import com.admin.app.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class LoginDBService {

    LoginMapper loginMapper;

    public LoginDBService(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }

    public Member login(String loginId){
        return loginMapper.findByLoginId(loginId);
    }

    public Member checkPassword(String loginId, String password){
        Member member = loginMapper.findByLoginId(loginId);
        Optional<Member> checkMember = Optional.ofNullable(member);
        return checkMember.filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }


    public int checkLoginCount(String loginId){
        return loginMapper.checkLoginCount(loginId);
    }

    public void increaseLoginCount(String loginId){
        loginMapper.increaseLoginCount(loginId);
    }

    public void lockAuthUser(String loginId){
        loginMapper.lockAuthUser(loginId);
    }

    public void updateLastLoginTime(String username){
        loginMapper.updateLastLoginTime(username);
    }
}
