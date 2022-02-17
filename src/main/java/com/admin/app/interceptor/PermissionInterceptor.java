package com.admin.app.interceptor;

import com.admin.app.SessionConst;
import com.admin.app.dto.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class PermissionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        ModelAndView modelAndView = new ModelAndView();
        log.info("권한 체크 인터셉터 실행 {}", requestURI);

        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        modelAndView.addObject("member",member);

//        if(!("SUPER_ADMIN").equals(member.getRole())){
//            //response.sendRedirect("/error");
//            response.setContentType("text/html; charset=utf-8");
//            PrintWriter printwriter = response.getWriter();
//            printwriter.print("<script>alert('권한이 없습니다.');</script>");
//            printwriter.flush();
//            printwriter.close();
//            return false;
//        }


        return true;
    }
}
