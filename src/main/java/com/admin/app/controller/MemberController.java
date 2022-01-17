package com.admin.app.controller;

import com.admin.app.dto.EditMember;
import com.admin.app.dto.Member;
import com.admin.app.dto.PagingInfo;
import com.admin.app.service.MemberService;
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
        return ResponseEntity.ok().body(member);

    }

    @RequestMapping(value={"/edit"}, method=RequestMethod.POST)
    public ResponseEntity<HashMap<String,Object>>  editAuthAdminList(HttpServletRequest request, @RequestBody EditMember member) {
        HashMap<String,Object> map = new HashMap<>();

        try{
            if (member.getEditType().equals("add")) {
                System.out.println("사용자 추가");
                map.put("message", "SUCCESS");
            } else if (member.getEditType().equals("update")) {

            }

        }catch(Exception e){

        }

        return  ResponseEntity.ok().body(map);

//        try {
//            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
//            if (uxUser.getEditType().equals("add")) {
//                uxUser.setPassword(passwordEncoder.encode("asdf1234@@!!"));
//                authService.insertAuthAdminList(uxUser);
//                authService.insertAuthRole(uxUser);
//                uxUser.setType("add");
//            } else if (uxUser.getEditType().equals("update")) {
//                authService.updateAuthAdminList(uxUser);
//                uxUser.setType("role");
//            } else if(uxUser.getEditType().equals("edit")){
//                UxUser user = authService.checkPassword(uxUser);
//                if(BCrypt.checkpw(uxUser.getPassword(),user.getPassword())){
//                    if(uxUser.getNewpassword().equals(uxUser.getPassword())){
//                        return ResponseEntity.ok().body(new UxResponse(DefResponse.ERR909));
//                    } else if(uxUser.getNewpassword().equals(uxUser.getEmail())){
//                        return ResponseEntity.ok().body(new UxResponse(DefResponse.ERR909));
//                    }
//                    uxUser.setPassword(passwordEncoder.encode(uxUser.getNewpassword()));
//                    authService.updateUserPassword(uxUser);
//                    uxUser.setType("edit");
//                } else {
//                    return ResponseEntity.ok().body(new UxResponse(DefResponse.ERR906));
//                }
//            }
//            authService.updateAuthHistory(uxUser);
//            resCode.writeUpdateLog(request,uxUser,uxUser.getId());
//        } catch (DataAccessException e) {
//            resCode.writeUpdateFailLog(request,uxUser);
//            return ResponseEntity.ok().body(new UxResponse(DefResponse.ERR9XX));
//        }
//
//        return ResponseEntity.ok().body(new UxResponse(DefResponse.SUCCESS));
    }
}
