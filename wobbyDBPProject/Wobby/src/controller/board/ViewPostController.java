package controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import service.CommunityManager;
import service.dto.BoardDTO;
import service.dto.CommentDTO;
import service.dto.PostDTO;

public class ViewPostController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {	     
        HttpSession session = request.getSession();
        String userId = UserSessionUtils.getLoginUserId(session);		
    	PostDTO post = null;
    	BoardDTO board = new BoardDTO();
    	CommunityManager commManager = CommunityManager.getInstance();
    	
    	List<CommentDTO> commentList = (List<CommentDTO>) request.getAttribute("commentList");
	    List<CommentDTO> popularPostList = null;
	    
        int postId = Integer.parseInt(request.getParameter("postId"));
        int boardId = Integer.parseInt(request.getParameter("boardId")); 
        
        board = commManager.findBoard(boardId);
        String boardName = board.getName();
        System.out.println("boardName viewpost= " + boardName);
        request.setAttribute("boardName", boardName);
        
        if(commentList == null) {
        	 commentList = commManager.findCommentList(postId);
        }
		post = commManager.findPost(postId);
		commManager.increasePostViewCnt(postId);
		
		
		String pUserId = post.getUserId();
		System.out.println(pUserId);
		
		System.out.println(session.getAttribute(UserSessionUtils.USER_SESSION_KEY));
		System.out.println(session.getAttribute("curUserId"));
		if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		
        }
		request.setAttribute("boardName", boardName);
		request.setAttribute("post", post);	
		request.setAttribute("commentList", commentList);
		if (commManager.existingScrap(userId, postId)) {
            request.setAttribute("isScraped", true);
        }
		return "/board/postDetail.jsp?boardId=" + boardId + "&postId=" + postId;
    }
}