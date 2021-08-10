package controller.scrap;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import service.CommunityManager;
import service.dto.PostDTO;


public class DeleteScrapController implements Controller{
    @Override
       public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {   
         CommunityManager manager = CommunityManager.getInstance();
         HttpSession session = request.getSession(); 
         
         if (!UserSessionUtils.hasLogined(request.getSession())) {
             return "redirect:/user/login/form";     // login form �슂泥��쑝濡� redirect
         }
         String userId = UserSessionUtils.getLoginUserId(session);
         System.out.println("*");
         String[] deleteScrapList = request.getParameterValues("delete");
         int scrapNo;
         int postId;
      
      try {
          for(String deleteScrapNo : deleteScrapList) {
              System.out.println("*" + deleteScrapNo);
              scrapNo = Integer.parseInt(deleteScrapNo);
              postId = manager.getPostIdbyScrap(scrapNo);
              manager.deleteScrap(scrapNo, postId);
          }
         
         return "redirect:/user/myPage/scrap/list";         
      } catch (Exception e) {
          request.setAttribute("Failed", true);
         request.setAttribute("exception", e);
         return "/user/myPage/scrap/list.jsp";         
      }   
    }
}