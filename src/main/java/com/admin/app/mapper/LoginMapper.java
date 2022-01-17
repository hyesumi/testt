package com.admin.app.mapper;

import com.admin.app.dto.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

@Mapper
public interface LoginMapper {

    Member findByLoginId(String loginId) throws DataAccessException;

    int checkLoginCount(String loginId)throws DataAccessException;

    void increaseLoginCount(String loginId)throws DataAccessException;

    void lockAuthUser(String loginId)throws DataAccessException;

    void updateLastLoginTime(String loginId)throws DataAccessException;
}
