package com.example.controller;

import com.example.SessionConst;
import com.example.login.LoginForm;
import com.example.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String homeLoginV3(@ModelAttribute("loginForm") LoginForm loginForm, HttpServletRequest request, Model model){

        HttpSession session = request.getSession(false);
        if(session == null){
            return "home";
        }
        Member loginmember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);

        if(loginmember ==null){
            return "home";
        }

        model.addAttribute("member",loginmember);
        return "view/hello";
    }

}
