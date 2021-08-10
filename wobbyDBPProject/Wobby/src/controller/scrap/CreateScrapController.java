package controller.scrap;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import service.CommunityManager;
import service.dto.PostDTO;


public class CreateScrapController implements Controller{
     @Override
        public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
         CommunityManager manager = CommunityManager.getInstance();
         HttpSession session = request.getSession(); 
         
         if (!UserSessionUtils.hasLogined(request.getSession())) {
             return "redirect:/user/login/form";     // login form �슂泥��쑝濡� redirect
         }
         String userId = UserSessionUtils.getLoginUserId(session);
         int postId = Integer.parseInt(request.getParameter("postId"));
         int boardId = Integer.parseInt(request.getParameter("board_Id"));
        // PostDTO post = new PostDTO();
        
        try {
            manager.createScrap(userId, postId);
          //  post = manager.findPost(postId);

          //  request.setAttribute("boardId", boardId);
          //  request.setAttribute("postId", postId);
            request.setAttribute("scrapSuccessed", true);
            return "/community/post?boardId=" + boardId +"&postId=" + postId; 
        } catch (Exception e) {
          request.setAttribute("Failed", true);
          request.setAttribute("exception", e);
          System.out.println(e);
          return "redirect:/community/post?boardId=" + boardId + "&postId=" + postId;           
        }   
     }
}