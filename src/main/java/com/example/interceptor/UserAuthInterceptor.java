package com.example.interceptor;

import com.example.SessionConst;
import com.example.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Slf4j
public class UserAuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        ModelAndView modelAndView = new ModelAndView();
        log.info("인증,권한 체크 인터셉터 실행 {}", requestURI);

        HttpSession session = request.getSession();

        if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null){
            log.info("미인증 사용자 요청");
            response.setContentType("text/html; charset=utf-8");
            PrintWriter printwriter = response.getWriter();
            printwriter.print("<script>alert('세션이 만료되어 다시 로그인 부탁드립니다.'); location.href='/login';</script>");
            printwriter.flush();
            printwriter.close();
            return false;
        }else{
            Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
            modelAndView.addObject("member",member);
            if(member.getId() != 1){
                //response.sendRedirect("/error");
                response.setContentType("text/html; charset=utf-8");
                PrintWriter printwriter = response.getWriter();
                printwriter.print("<script>alert('권한이 없습니다.');</script>");
                printwriter.flush();
                printwriter.close();
                return false;
            }
        }

        return true;
    }
}
