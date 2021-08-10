package controller.board;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import service.CommunityManager;

public class DeletePostController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
        CommunityManager commManager = CommunityManager.getInstance();        
        int postId = Integer.parseInt(request.getParameter("postId"));
        int boardId = Integer.parseInt(request.getParameter("board_Id"));

        try {
        	request.setAttribute("boardId", boardId);
        	commManager.removePost(postId);               // 글 삭제
        	return "redirect:/community/board?boardId=" + boardId ;    //글 리스트로 이동
        } catch(Exception e) {
        	/* 삭제가 불가능한 경우 */
            request.setAttribute("postId", postId);                     
            request.setAttribute("deleteFailed", true); 
            request.setAttribute("exception", new IllegalStateException("관리자 혹은 글 작성자가 아닌 경우 글을 지울 수 없습니다."));            
            return "/board/postDetail.jsp?boardId=" + boardId + "?postId=" + postId;
        }
//        http://localhost:8080/Wobby/community/board?boardId=3
    }
}
