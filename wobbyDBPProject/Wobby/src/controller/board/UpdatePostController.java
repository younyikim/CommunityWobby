package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import service.CommunityManager;
import service.dto.PostDTO;

public class UpdatePostController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
      
	     HttpSession session = request.getSession();
    	 String userId = UserSessionUtils.getLoginUserId(session);
	     
        // 1) GET request: 글 수정 form 요청    
		if (request.getMethod().equals("GET")) {
			CommunityManager commManager = CommunityManager.getInstance();
			 int postId = Integer.parseInt(request.getParameter("postId"));

			 if (UserSessionUtils.isLoginUser(userId, session) ||
						UserSessionUtils.isLoginUser("admin", session)) {
				 PostDTO post = commManager.findPost(postId);	// 수정하려는 글 정보 검색
				 request.setAttribute("post", post);	
				 return "/board/postUpdate.jsp";   // 검색한 정보를 update form으로 전송    
			 }
			 request.setAttribute("exception", new IllegalStateException("글 작성자가 아닌 경우 글을 수정할 수 없습니다."));       
//           return "redirect:/community/board?boardId=" + boardId ;
	    }	
    	
    	// 2) POST request (글 정보가 parameter로 전송됨)
	   int boardIdUpdate = Integer.parseInt(request.getParameter("updateboardId"));
       PostDTO post = new PostDTO(
    		   Integer.parseInt(request.getParameter("updatepostId")), 
    		   boardIdUpdate,
    		   request.getParameter("userId"),  
    		   request.getParameter("title"), 
    		   request.getParameter("contents"));
       
       System.out.println("post로 넘어온 게시글");
       CommunityManager commManager = CommunityManager.getInstance();
       commManager.update(post);					
       return "redirect:/community/board?boardId=" + boardIdUpdate ;    //글 리스트로 이동
        
    }
}
