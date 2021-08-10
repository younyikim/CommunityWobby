package controller.board;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import service.CommunityManager;
import service.dto.PostDTO;

public class ListClubPostController implements Controller {
    @SuppressWarnings("unchecked")
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
        CommunityManager commManager = CommunityManager.getInstance();
        List<PostDTO> postList = (List<PostDTO>) request.getAttribute("postList");
        List<PostDTO> popularPostList = null;
        
        String clubId = request.getParameter("clubId");
        int boardId = commManager.findBoardId(clubId);
        
        if (postList == null) { 
            System.out.println("boardId : " + boardId);
            postList = commManager.findPostList(boardId);
            popularPostList = commManager.findPopularPostList(boardId);
            request.setAttribute("popularPostList", popularPostList);
            
            request.setAttribute("postList", postList); 

            return "/board/boardList.jsp?boardId=" + boardId ;   
        }

		request.setAttribute("postList", postList);	
		String keyword = request.getParameter("keyword");
		return "/board/boardList.jsp?search=" + keyword ;     
    } 
}
