package com.admin.app.mapper;

import com.admin.app.dto.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface LoginMapper {

    Member findByLoginId(String email) throws DataAccessException;
}
