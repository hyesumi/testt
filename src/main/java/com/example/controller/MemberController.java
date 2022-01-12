package com.example.controller;

import com.example.dto.Member;
import com.example.dto.PagingInfo;
import com.example.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("member")
public class MemberController {

    private MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @RequestMapping("/list")
    public String memberList(Model model, HttpServletRequest request) {
        PagingInfo pagingInfo = new PagingInfo();
        pagingInfo.setCurrentPage(1);
//
//        Member member = new Member();
//        member.setLoginId("test");
//        member.setPassword("test!");
//        member.setName("테스터");
//
//        List<Member> memberList = new ArrayList<>();
//        memberList.add(member);
//

        List<Member> memberList = memberService.getMemberList();
        System.out.println(memberList);
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

    @RequestMapping(value={"/detail"}, method= RequestMethod.POST)
    public ResponseEntity<Member>detailAuthManager(@RequestParam(required = false) String email) {

        Member member = memberService.findUserByEmail(email);
        System.out.println(member.toString());
        return ResponseEntity.ok().body(member);


//        String registerid = SecurityContextHolder.getContext().getAuthentication().getName();
//        String key = environment.getProperty("uuid.aes.key");
//
//        UxUser manager = authService.findUserByEmail(email);
//        List<UxUser> userList = authService.findUserRole(registerid);
//        manager.setAuthRole(userList.get(0).getRole());
//
//        log.info(String.format("detailAuthManager|%s|%s|%s|%s|%s|%s","사용자조회","search",AESCrypto.encrypt(key,registerid),commonService.getRemoteAddr(request),commonService.getRemoteHost(request),AESCrypto.encrypt(key,email)));
//
//        return ResponseEntity.ok().body(manager);

    }
}
