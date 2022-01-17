package com.admin.app.service;

import com.admin.app.dto.Member;
import com.admin.app.mapper.MemberMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MemberService {
    MemberMapper memberMapper;

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public List<Member> getMemberList() {
        return memberMapper.getMemberList();
    }

    public Member findUserByEmail(String email){
        return memberMapper.findUserByEmail(email);
    }

    public void updateAuthAdminList(Member member){
        memberMapper.updateAuthAdminList(member);
    }

    public int insertAuthAdminList(Member uxUser){
        return memberMapper.insertAuthAdminList(uxUser);
    }
}
