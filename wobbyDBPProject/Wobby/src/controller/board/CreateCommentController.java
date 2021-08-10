package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import service.CommunityManager;
import service.dto.CommentDTO;

public class CreateCommentController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String userId = UserSessionUtils.getLoginUserId(session);
        int postId = Integer.parseInt(request.getParameter("postId"));
        int boardId = Integer.parseInt(request.getParameter("boardId"));

        System.out.println("*");
        String contents = request.getParameter("contents");
        System.out.println(userId + " " + contents);
        CommentDTO comment = new CommentDTO(postId, userId, contents);
        System.out.println(comment.getUserId() + " " + comment.getPostId() + " " + comment.getContents());
        
        try {
            //CommunityManager占쎈퓠 create筌ｌ꼶 봺 占쎌맄占쎌뿫
            CommunityManager commManager = CommunityManager.getInstance();
            int result = commManager.createComment(comment);
            System.out.println(result);
            
            return "redirect:/community/post?boardId=" + boardId + "&postId=" + postId;
        } catch (Exception e) {     // 占쎌굙占쎌뇚 獄쏆뮇源  占쎈뻻 占쎌뿯占쎌젾 form占쎌몵嚥∽옙 forwarding
            request.setAttribute("creationFailed", true);
            request.setAttribute("exception", e);
            return "redirect:/community/post?boardId=" + boardId + "&postId=" + postId;
        }
    }
}