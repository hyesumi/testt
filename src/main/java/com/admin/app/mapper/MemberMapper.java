package com.admin.app.mapper;

import com.admin.app.dto.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {
    List<Member> getMemberList() throws DataAccessException;

    Member findUserByEmail(String email) throws DataAccessException;

    void updateAuthAdminList(Member member) throws DataAccessException;

    int insertAuthAdminList(Member member) throws DataAccessException;
}
