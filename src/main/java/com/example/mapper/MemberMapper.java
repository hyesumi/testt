package com.example.mapper;

import com.example.dto.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.xml.crypto.Data;
import java.util.List;

@Mapper
public interface MemberMapper {
    List<Member> getMemberList() throws DataAccessException;

    Member findUserByEmail(String email) throws DataAccessException;
}
