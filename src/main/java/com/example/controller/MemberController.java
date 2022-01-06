package com.example.controller;

import com.example.dto.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("member")
public class MemberController {
    @RequestMapping("/list")
    public String memberListApp(Model model, HttpServletRequest request) {

        Member member = new Member();
        member.setLoginId("test");
        member.setPassword("test!");
        member.setName("테스터");

        List<Member> memberList = new ArrayList<>();
        memberList.add(member);

        model.addAttribute("memberList",memberList);
        model.addAttribute("totalSize",memberList.size());

        return "view/member/list";
    }
}
