package com.admin.app.mapper;


import com.admin.app.dto.Member;
import com.admin.app.dto.PagingInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapper {
    List<Member> getMemberList(PagingInfo pagingInfo) throws DataAccessException;

    Member findUserByEmail(String email) throws DataAccessException;

    void updateAuthAdminList(Member member) throws DataAccessException;

    int insertAuthAdminList(Member member) throws DataAccessException;

    void deleteAuthUser(Map<String,Object> idxArray) throws DataAccessException;

    Member checkPassword(String loginId) throws DataAccessException;

    void updateUserPassword(Member member) throws DataAccessException;

}
