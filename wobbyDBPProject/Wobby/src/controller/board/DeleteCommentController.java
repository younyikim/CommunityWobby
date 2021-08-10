package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import service.CommunityManager;

public class DeleteCommentController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {      
        HttpSession session = request.getSession();
        String userId = UserSessionUtils.getLoginUserId(session);
        CommunityManager commManager = CommunityManager.getInstance();   
        
        int postId = Integer.parseInt(request.getParameter("postId"));
        int boardId = Integer.parseInt(request.getParameter("boardId"));
        int commentId = Integer.parseInt(request.getParameter("commentId"));

        try {
            commManager.delete(commentId);            
            return "redirect:/community/post?boardId=" + boardId + "&postId=" + postId;
        } catch(Exception e) {
         
            request.setAttribute("postId", postId);                     
            request.setAttribute("deleteFailed", true); 
            return "redirect:/community/post?boardId=" + boardId + "&postId=" + postId;
        }
//        http://localhost:8080/Wobby/community/board?boardId=3
    }
}
