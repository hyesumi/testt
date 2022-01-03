package com.example.controller;

import com.example.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String homeLoginV3Spring(@SessionAttribute(name="LoginMembers",required = false) Member loginMember, Model model){
        if(loginMember == null){
            System.out.println("test");
            return "redirect:/login";
        }
        model.addAttribute("member",loginMember);
        return "/view/hello";
    }
}
