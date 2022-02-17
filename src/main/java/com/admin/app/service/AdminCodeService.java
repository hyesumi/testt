package com.admin.app.service;

import com.admin.app.dto.AdminCode;
import com.admin.app.mapper.AdminCodeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminCodeService {
    AdminCodeMapper adminCodeMapper;

    public AdminCodeService(AdminCodeMapper adminCodeMapper) {
        this.adminCodeMapper = adminCodeMapper;
    }

    public List<AdminCode> getAdminCodeList(AdminCode adminCode) {
        return adminCodeMapper.getAdminCodeList(adminCode);
    }
    public int adminCodeTotalCnt(AdminCode adminCode) {
        return adminCodeMapper.adminCodeTotalCnt(adminCode);
    }

    public AdminCode findAdminCodeByIdx(String id) {
        return adminCodeMapper.findAdminCodeByIdx(id);
    }

    public String findDetailCode(String sidoContent, String gugunContent) {
        return adminCodeMapper.findDetailCode(sidoContent, gugunContent);
    }

    public String findTaxiType(String areaName,String taxiType) {
        return adminCodeMapper.findTaxiType(areaName, taxiType);
    }

    public List<AdminCode> getFareList() {
        return adminCodeMapper.getFareList();
    }

    public int insertAdminCode(AdminCode adminCode){
        return adminCodeMapper.insertAdminCode(adminCode);
    }

    public List<AdminCode> getAdminCode(AdminCode adminCode) {
        return adminCodeMapper.getAdminCode(adminCode);
    }

    public void updateAdminCode(AdminCode adminCode){
        adminCodeMapper.updateAdminCode(adminCode);
    }
}

