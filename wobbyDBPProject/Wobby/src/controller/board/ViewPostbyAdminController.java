package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import service.CommunityManager;
import service.dto.PostDTO;

public class ViewPostbyAdminController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {          
        PostDTO post = null;
        CommunityManager commManager = CommunityManager.getInstance();
        
        int postId = Integer.parseInt(request.getParameter("postId"));
        int boardId = Integer.parseInt(request.getParameter("boardId"));
        post = commManager.findPost(postId);
        commManager.increasePostViewCnt(postId);
        request.setAttribute("post", post); 			
		return "/admin/post.jsp?boardId=" + boardId + "?postId=" + postId;
    }
}
