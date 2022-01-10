package com.example.mapper;

import com.example.dto.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public interface MemberMapper {
    List<Member> getMemberList();
}
