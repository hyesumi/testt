package com.admin.app.controller;

import com.admin.app.SessionConst;
import com.admin.app.dto.Member;
import com.admin.app.login.LoginForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    @RequestMapping(value="/")
    public String login(@ModelAttribute("loginForm") LoginForm loginForm, HttpServletRequest request, Model model){

        HttpSession session = request.getSession(false);
        if(session == null){
            return "redirect:/login";
        }

        Member loginmember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);

        if(loginmember ==null){
            return "redirect:/login";
        }

        model.addAttribute("member",loginmember);
        return "redirect:/member/list";
    }

    @RequestMapping(value="/noAuth")
    public String noAuthentication(){

        return "/noAuth";
    }
}
