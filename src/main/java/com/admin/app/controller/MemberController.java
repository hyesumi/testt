package com.admin.app.controller;

import com.admin.app.dto.EditMember;
import com.admin.app.dto.Member;
import com.admin.app.dto.PagingInfo;
import com.admin.app.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("member")
public class MemberController {

    private MemberService memberService;

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

        List<Member> memberList = memberService.getMemberList(pagingInfo);

        model.addAttribute("currentPage", 1);
        model.addAttribute("totalSize", memberList.size());
        model.addAttribute("memberList", memberList);
        model.addAttribute("perPage", pagingInfo.getPerPage());

        return "view/member/list";
    }

    @RequestMapping(value="/search", method={RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody Map<String, Object> searchListAdmin(@RequestBody Map<String, Object> model) {

        PagingInfo pagingInfo = new PagingInfo();
        pagingInfo.setCurrentPage((Integer) model.get("currentPage"));
        List<Member> list = memberService.getMemberList(pagingInfo);

        model.put("memberList",list);
        model.put("totalSize",list.size());
        model.put("perPage", pagingInfo.getPerPage());

        return model;
    }

    @RequestMapping(value={"/detail"}, method= RequestMethod.POST)
    public ResponseEntity<Member>detailAuthManager(@RequestParam(required = false) String email) {

        Member member = memberService.findUserByEmail(email);
        return ResponseEntity.ok().body(member);

    }

    @RequestMapping(value={"/edit"}, method=RequestMethod.POST)
    public ResponseEntity<HashMap<String,Object>>  editAuthAdminList(HttpServletRequest request, @RequestBody EditMember member) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("message", "SUCCESS");
        try{
            if (member.getEditType().equals("add")) {
                member.setPassword(BCrypt.hashpw("asdf1234@@!!",BCrypt.gensalt(10)));
                memberService.insertAuthAdminList(member);
            } else if (member.getEditType().equals("update")) {
                memberService.updateAuthAdminList(member);
            }  else if (member.getEditType().equals("edit")) {
                Member checkMember = memberService.checkPassword(member.getLoginId());
                if(BCrypt.checkpw(member.getPassword(),checkMember.getPassword())){
                    if(member.getNewpassword().equals(member.getPassword())){
                        map.put("message","동일한 패스워드로는 변경불가능합니다.");
                        return ResponseEntity.ok().body(map);
                    } else if(member.getNewpassword().equals(member.getLoginId())){
                        map.put("message","아이디와 동일한 패스워드로는 변경불가능합니다.");
                        return ResponseEntity.ok().body(map);
                    }
                    member.setPassword(BCrypt.hashpw(member.getNewpassword(),BCrypt.gensalt(10)));
                    memberService.updateUserPassword(member);
                } else {
                     map.put("message","패스워드가 잘못입력되었습니다.");
                }
            }

        }catch(Exception e){
            map.put("message", "등록에 실패하였습니다. 관리자에게 문의하세요");
        }

        return ResponseEntity.ok().body(map);
    }

    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public ResponseEntity<HashMap<String,Object>> deleteAuthAdminList(HttpServletRequest request, @RequestBody Map<String,Object> idxArray) {
        HashMap<String,Object> map = new HashMap<>();
        try {
            memberService.deleteAuthUser(idxArray);
            map.put("message", "SUCCESS");

        } catch (DataAccessException e) {
            map.put("message", "FAIL");
            return  ResponseEntity.ok().body(map);
        }

        return  ResponseEntity.ok().body(map);
    }
}
