package controller.board;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import service.CommunityManager;
import service.dto.BoardDTO;
import service.dto.PostDTO;

public class ListPostController implements Controller {
    @SuppressWarnings("unchecked")
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
        CommunityManager commManager = CommunityManager.getInstance();
        List<PostDTO> postList = (List<PostDTO>) request.getAttribute("postList");
        List<PostDTO> popularPostList = null;
        BoardDTO board = new BoardDTO();
        String boardName = null;
        
        if (postList == null) { //null일경우, 즉 검색 / 매칭이 아닐경우
            int boardId = Integer.parseInt(request.getParameter("boardId"));
            System.out.println("boardId : " + boardId);
            postList = commManager.findPostList(boardId);
            popularPostList = commManager.findPopularPostList(boardId);
            request.setAttribute("popularPostList", popularPostList);
            
            board = commManager.findBoard(boardId);
            boardName = board.getName();
            System.out.println("boardName listpost= " + boardName);
            request.setAttribute("boardName", boardName);
            
            
            request.setAttribute("postList", postList); 

            return "/board/boardList.jsp?boardId=" + boardId ;   
        }
        request.setAttribute("boardName", boardName);
		request.setAttribute("postList", postList);	
		String keyword = request.getParameter("keyword");
		return "/board/boardList.jsp?search=" + keyword ;     
    } 
}