package controller.user;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import controller.Controller;

public class HomeController implements Controller {
    
     @Override
        public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
         // 로그인 여부 확인
         if (!UserSessionUtils.hasLogined(request.getSession())) {
             return "/user/wobbyHome.jsp";
         }
         else {
             request.setAttribute("curUserId", UserSessionUtils.getLoginUserId(request.getSession()));   
             return "/user/wobbyHome_login.jsp";
         }
        }
}