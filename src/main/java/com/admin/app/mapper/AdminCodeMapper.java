package com.admin.app.mapper;


import com.admin.app.dto.AdminCode;
import com.admin.app.dto.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminCodeMapper {
    List<AdminCode> getAdminCodeList(AdminCode adminCode) throws DataAccessException;

    int adminCodeTotalCnt(AdminCode adminCode) throws DataAccessException;

    AdminCode findAdminCodeByIdx(String id) throws DataAccessException;

    void updateAuthAdminList(Member member) throws DataAccessException;

    int insertAuthAdminList(Member member) throws DataAccessException;

    void deleteAuthUser(Map<String,Object> idxArray) throws DataAccessException;

    Member checkPassword(String loginId) throws DataAccessException;

    void updateUserPassword(Member member) throws DataAccessException;

    String findDetailCode(String sidoContent, String gugunContent) throws DataAccessException;

    String findTaxiType(String areaName,String taxiType) throws DataAccessException;

    List<AdminCode> getFareList() throws DataAccessException;

    int insertAdminCode(AdminCode adminCode) throws DataAccessException;

    List<AdminCode> getAdminCode(AdminCode adminCode) throws DataAccessException;

    void updateAdminCode(AdminCode adminCode) throws DataAccessException;
}
