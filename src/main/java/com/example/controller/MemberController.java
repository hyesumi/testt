package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("member")
public class MemberController {
    @RequestMapping("/list")
    public String memberListApp(Map<String, Object> model, HttpServletRequest request) {

        model.put("currentPage", 1);

        System.out.println("test");

        return "view/member/list";
    }
}
