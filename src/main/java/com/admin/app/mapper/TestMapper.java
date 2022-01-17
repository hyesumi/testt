package com.admin.app.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {
    String selectList();
}