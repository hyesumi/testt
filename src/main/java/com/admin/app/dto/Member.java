package com.admin.app.dto;

import lombok.Data;


@Data
public class Member {

    private Long id;
    private String loginId;
    private String name;
    private String password;
    private String role;
    private String createdDt;
}
