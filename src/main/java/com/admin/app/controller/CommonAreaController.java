package com.admin.app.controller;

import com.admin.app.dto.CommonArea;
import com.admin.app.dto.PagingInfo;
import com.admin.app.service.CommonAreaService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
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
@RequestMapping("common")
public class CommonAreaController {

    private CommonAreaService commonAreaService;

    public CommonAreaController(CommonAreaService commonAreaService) {
        this.commonAreaService = commonAreaService;
    }

    @RequestMapping("/list")
    public String commonAreaList(Model model, HttpServletRequest request) {
        PagingInfo pagingInfo = new PagingInfo();
        pagingInfo.setCurrentPage(1);

        List<CommonArea> commonAreaList = commonAreaService.getCommonBusinessAreaList(pagingInfo);
        for(CommonArea commonArea : commonAreaList){
            if(commonArea.getExtraData() != null && !("").equals(commonArea.getExtraData())){
                JSONObject jObj = (JSONObject) JSONValue.parse(commonArea.getExtraData());
                JSONArray jArray = (JSONArray) jObj.get("taxi_type");
                ArrayList<String> list = new ArrayList<>();
                jArray.forEach(k ->{
                    if(("1").equals(k)){
                        k = "중형";
                    }else if(("2").equals(k)){
                        k = "대형,모범";
                    }else if(("w").equals(k)){
                        k = "약자택시";
                    }
                    list.add((String)k);
                });

                jObj.replace("taxi_type",list);
                commonArea.setExtraData(jObj.toString());
            }
            if("0000-00-00 00:00:00".equals(commonArea.getEffectiveTs())){
                commonArea.setEffectiveTs("즉시시행");
            }
        }
        model.addAttribute("currentPage", 1);
        model.addAttribute("totalSize", commonAreaList.size());
        model.addAttribute("commonAreaList", commonAreaList);
        model.addAttribute("perPage", pagingInfo.getPerPage());

        return "view/common/list";
    }

//    @RequestMapping(value="/search", method={RequestMethod.POST, RequestMethod.GET})
//    public @ResponseBody Map<String, Object> searchListAdmin(@RequestBody Map<String, Object> model) {
//
//        PagingInfo pagingInfo = new PagingInfo();
//        pagingInfo.setCurrentPage((Integer) model.get("currentPage"));
//        List<CommonArea> list = commonAreaService.getMemberList(pagingInfo);
//
//        model.put("memberList",list);
//        model.put("totalSize",list.size());
//        model.put("perPage", pagingInfo.getPerPage());
//
//        return model;
//    }

    @RequestMapping(value={"/detail"}, method= RequestMethod.POST)
    public ResponseEntity<CommonArea> detailCommonAreaManager(@RequestParam(required = false) String id) {

        CommonArea commonArea = commonAreaService.findCommonAreaById(id);
        return ResponseEntity.ok().body(commonArea);

    }

    @RequestMapping(value={"/edit"}, method=RequestMethod.POST)
    public ResponseEntity<HashMap<String,Object>>  editAuthAdminList(HttpServletRequest request, @RequestBody CommonArea commonArea) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("message", "SUCCESS");
        try{
            System.out.println(commonArea);
        }catch(Exception e){
            map.put("message", "등록에 실패하였습니다. 관리자에게 문의하세요");
        }
        return ResponseEntity.ok().body(map);
    }

    @RequestMapping(value={"/searchCode"}, method= RequestMethod.POST)
    public ResponseEntity<CommonArea> searchCode(@RequestParam(required = false) String name) {

        CommonArea commonArea = commonAreaService.findCommonAreaById(name);
        return ResponseEntity.ok().body(commonArea);

    }


//    @RequestMapping(value={"/edit"}, method=RequestMethod.POST)
//    public ResponseEntity<HashMap<String,Object>>  editAuthAdminList(HttpServletRequest request, @RequestBody EditMember member) {
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("message", "SUCCESS");
//        try{
//            if (member.getEditType().equals("add")) {
//                member.setPassword(BCrypt.hashpw("asdf1234@@!!",BCrypt.gensalt(10)));
//                memberService.insertAuthAdminList(member);
//            } else if (member.getEditType().equals("update")) {
//                memberService.updateAuthAdminList(member);
//            }  else if (member.getEditType().equals("edit")) {
//                Member checkMember = memberService.checkPassword(member.getLoginId());
//                if(BCrypt.checkpw(member.getPassword(),checkMember.getPassword())){
//                    if(member.getNewpassword().equals(member.getPassword())){
//                        map.put("message","동일한 패스워드로는 변경불가능합니다.");
//                        return ResponseEntity.ok().body(map);
//                    } else if(member.getNewpassword().equals(member.getLoginId())){
//                        map.put("message","아이디와 동일한 패스워드로는 변경불가능합니다.");
//                        return ResponseEntity.ok().body(map);
//                    }
//                    member.setPassword(BCrypt.hashpw(member.getNewpassword(),BCrypt.gensalt(10)));
//                    memberService.updateUserPassword(member);
//                } else {
//                     map.put("message","패스워드가 잘못입력되었습니다.");
//                }
//            }
//
//        }catch(Exception e){
//            map.put("message", "등록에 실패하였습니다. 관리자에게 문의하세요");
//        }
//
//        return ResponseEntity.ok().body(map);
//    }
//
//    @RequestMapping(value="/delete", method=RequestMethod.POST)
//    public ResponseEntity<HashMap<String,Object>> deleteAuthAdminList(HttpServletRequest request, @RequestBody Map<String,Object> idxArray) {
//        HashMap<String,Object> map = new HashMap<>();
//        try {
//            memberService.deleteAuthUser(idxArray);
//            map.put("message", "SUCCESS");
//
//        } catch (DataAccessException e) {
//            map.put("message", "FAIL");
//            return  ResponseEntity.ok().body(map);
//        }
//
//        return  ResponseEntity.ok().body(map);
//    }
}
