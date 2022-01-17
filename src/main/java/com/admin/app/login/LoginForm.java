package com.admin.app.login;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginForm {

    private String loginId;

    private String password;
}
