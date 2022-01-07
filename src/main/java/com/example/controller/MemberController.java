package com.example.controller;

import com.example.dto.Member;
import com.example.dto.PagingInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
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
    public String memberList(Model model, HttpServletRequest request) {
        PagingInfo pagingInfo = new PagingInfo();
        pagingInfo.setCurrentPage(1);

        Member member = new Member();
        member.setLoginId("test");
        member.setPassword("test!");
        member.setName("테스터");

        List<Member> memberList = new ArrayList<>();
        memberList.add(member);

        model.addAttribute("memberList",memberList);
        model.addAttribute("totalSize",memberList.size());
        model.addAttribute("perPage", pagingInfo.getPerPage());
        return "view/member/list";
    }

    @RequestMapping("/reg")
    public ResponseEntity<HashMap<String,Object>> editAuthAdminList(@RequestBody Member member) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("message", "SUCCESS");

        return ResponseEntity.ok().body(map);
    }
}
