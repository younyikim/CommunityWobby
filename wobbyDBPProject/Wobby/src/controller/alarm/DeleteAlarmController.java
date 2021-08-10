package controller.alarm;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import service.CommunityManager;


public class DeleteAlarmController implements Controller{
    @Override
       public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {   
         CommunityManager manager = CommunityManager.getInstance();
         HttpSession session = request.getSession(); 
         
         if (!UserSessionUtils.hasLogined(request.getSession())) {
             return "redirect:/user/login/form";     // login form �슂泥��쑝濡� redirect
         }
         String userId = UserSessionUtils.getLoginUserId(session);
         String[] deleteAlarmList = request.getParameterValues("delete");
         int alarmNo;
      
      try {
          for(String deleteAlarmNo : deleteAlarmList) {
              System.out.println("*" + deleteAlarmNo);
              alarmNo = Integer.parseInt(deleteAlarmNo);
              manager.deleteAlarm(alarmNo);
          }
         
         return "redirect:/user/myPage/alarm/list";         
      } catch (Exception e) {
          request.setAttribute("Failed", true);
         request.setAttribute("exception", e);
         return "/user/myPage/alarm/list.jsp";         
      }   
    }
}