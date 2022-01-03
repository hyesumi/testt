package com.example.controller;

import com.example.login.LoginForm;
import com.example.member.Member;
import com.example.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm){
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String loginV4(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult,
                          @RequestParam(defaultValue= "/") String redirectURL, HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }
        if(!StringUtils.hasText(form.getLoginId())){
            bindingResult.addError(new FieldError("form","loginId",form.getLoginId(),false,null,null,"아이디가 입력되지 않았습니다."));
            return "login/loginForm";
        }

        if(!StringUtils.hasText(form.getPassword())){
            bindingResult.addError(new FieldError("form","password",form.getPassword(),false,null,null,"패스워드가 입력되지 않았습니다."));
            return "login/loginForm";
        }

        Member loginMember = loginService.login(form.getLoginId(),form.getPassword());
        if(loginMember == null){
            bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }
        HttpSession session = request.getSession();
        session.setAttribute("member", loginMember);
        //sessionManager.createSession(loginMember,response);

        return "view/hello";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session !=null){
            session.invalidate();

        }
        return "redirect:/";
    }
}
