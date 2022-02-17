package com.admin.app.controller;

import com.admin.app.dto.AdminCode;
import com.admin.app.service.AdminCodeService;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("admincode")
public class AdminCodeController {

    private AdminCodeService adminCodeService;

    public AdminCodeController(AdminCodeService adminCodeService) {
        this.adminCodeService = adminCodeService;
    }

    @RequestMapping("/list")
    public String excpetionFareList(Model model, HttpServletRequest request) {
        AdminCode adminCode = new AdminCode();
        adminCode.setCurrentPage(1);

        List<AdminCode> adminCodeList = adminCodeService.getAdminCodeList(adminCode);

        for(AdminCode adminCode1:adminCodeList){
            if(adminCode1.getDepth2() == null || ("").equals(adminCode1.getDepth2())){
                adminCode1.setDepth2("전체");
            }
        }
        List<AdminCode> fareList = adminCodeService.getFareList();
        model.addAttribute("currentPage", 1);
        model.addAttribute("totalSize", adminCodeService.adminCodeTotalCnt(adminCode));
        model.addAttribute("adminCodeList", adminCodeList);
        model.addAttribute("perPage", adminCode.getPerPage());

        model.addAttribute("fareList", fareList);
        return "view/admincode/list";
    }

    @RequestMapping(value="/search", method={RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody Map<String, Object> searchListAdmin(@RequestBody Map<String, Object> model) {
        AdminCode adminCode = new AdminCode();
        adminCode.setCurrentPage((Integer) model.get("currentPage"));
        adminCode.setDepth1((String)model.get("searchName"));
        adminCode.setAdmcode((String)model.get("searchCode"));

        List<AdminCode> list = adminCodeService.getAdminCodeList(adminCode);
        for(AdminCode adminCode1:list){
            if(adminCode1.getDepth2() == null || ("").equals(adminCode1.getDepth2())){
                adminCode1.setDepth2("전체");
            }
        }
        model.put("adminCodeList", list);
        model.put("totalSize",adminCodeService.adminCodeTotalCnt(adminCode));
        model.put("perPage", adminCode.getPerPage());

        return model;
    }

    @RequestMapping(value={"/detail"}, method= RequestMethod.POST)
    public ResponseEntity<AdminCode> detailAuthManager(@RequestParam(required = false) String id) {
        AdminCode adminCode = adminCodeService.findAdminCodeByIdx(id);
        if(adminCode.getDepth2() == null || ("").equals(adminCode.getDepth2())){
            adminCode.setDepth2("전체");
        }
        return ResponseEntity.ok().body(adminCode);

    }

    @RequestMapping(value={"/detailCode"}, method= RequestMethod.POST)
    public ResponseEntity<AdminCode> detailCodeManager(@RequestParam(required = false) String sidoContent, @RequestParam(required = false) String gugunContent) {

        AdminCode adminCode = new AdminCode();
        System.out.println(sidoContent);
        String code = adminCodeService.findDetailCode(sidoContent,gugunContent);
        System.out.println(code);
        String fareTaxiType1 = adminCodeService.findTaxiType(sidoContent,"type1");
        String fareTaxiType2 = adminCodeService.findTaxiType(sidoContent,"type2");
        String fareTaxiTypeW = adminCodeService.findTaxiType(sidoContent,"typeW");

        adminCode.setAdmcode(code);
        adminCode.setFareType1(fareTaxiType1);
        adminCode.setFareType2(fareTaxiType2);
        adminCode.setFareTypeW(fareTaxiTypeW);

        return ResponseEntity.ok().body(adminCode);

    }


    @RequestMapping(value={"/edit"}, method=RequestMethod.POST)
    public ResponseEntity<HashMap<String,Object>>  editAuthAdminList(HttpServletRequest request, @RequestBody AdminCode adminCode) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("message", "SUCCESS");
        try{
            List<AdminCode> adminCodeList = adminCodeService.getAdminCode(adminCode);
            if (adminCode.getEditType().equals("add")) {
                for(AdminCode code : adminCodeList){
                    if(code.getAdmcode().equals(adminCode.getAdmcode())){
                        map.put("message","이미 등록된 코드입니다.");
                        return ResponseEntity.ok().body(map);
                    }
                    if(code.getDepth1().equals(adminCode.getDepth1())){
                        if(code.getDepth2().equals(adminCode.getDepth2())){
                            map.put("message","이미 등록된 지역입니다.");
                            return ResponseEntity.ok().body(map);
                        }
                    }
                    System.out.println("add");
                    //adminCodeService.insertAdminCode(adminCode);
                }

            } else if (adminCode.getEditType().equals("update")) {

                //adminCodeService.updateAdminCode(adminCode);
                System.out.println("update");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            map.put("message", "등록에 실패하였습니다. 관리자에게 문의하세요");
        }

        return ResponseEntity.ok().body(map);
    }
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
