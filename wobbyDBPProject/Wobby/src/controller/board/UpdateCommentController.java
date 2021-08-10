package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import service.CommunityManager;
import service.dto.CommentDTO;

public class UpdateCommentController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("시발");
         HttpSession session = request.getSession();
         String userId = UserSessionUtils.getLoginUserId(session);
         CommunityManager commManager = CommunityManager.getInstance();
         int postId = Integer.parseInt(request.getParameter("postId"));
         int boardId = Integer.parseInt(request.getParameter("boardId"));
         int commentId = Integer.parseInt(request.getParameter("commentId"));
         CommentDTO comment = commManager.findComment(commentId);
         
        // 1) GET request: 湲� �닔�젙 form �슂泥�    
        if (request.getMethod().equals("GET")) {
             if (!comment.getUserId().equals(userId)){
                 request.setAttribute("exception", new IllegalStateException("수정이 불가능합니다.")); 
                 return "redirect:/community/post?boardId=" + boardId + "&postId=" + postId;      
             }
            request.setAttribute("updateComment", comment);    
            request.setAttribute("isUpdateComment", true);  
            return "/community/post?boardId=" + boardId + "&postId=" + postId;
        }   

        System.out.println("ff3");
        // 2) POST request (湲� �젙蹂닿� parameter濡� �쟾�넚�맖)
       comment.setContents(request.getParameter("contents"));
       commManager.updateComment(comment);
       return "redirect:/community/post?boardId=" + boardId + "&postId=" + postId;
    }
}
