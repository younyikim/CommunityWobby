package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import service.CommunityManager;
import service.dto.BoardDTO;
import service.dto.PostDTO;

public class CreatePostController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
        HttpSession session = request.getSession();
        String userId = UserSessionUtils.getLoginUserId(session);
        int boardId = Integer.parseInt(request.getParameter("boardId"));
        
        //받아온 정보를 토대로 post 객체 생성
        String title = request.getParameter("title");
        String content = request.getParameter("contents");
        String boardName = request.getParameter("boardName");
        PostDTO post = new PostDTO(boardId, userId, title, content);
        
        try {
            //CommunityManager에 create처리 위임
            CommunityManager commManager = CommunityManager.getInstance();
            
            int postId = commManager.create(post, boardId);
            System.out.println(postId);
//            return "redirect:/board/postDetail.jsp?boardId=" + boardId + "?postId=" + postId; // 성공 시 글로 redirect
            return "redirect:/community/board?boardId=" + boardId ;    //글 리스트로 이동
        } catch (Exception e) {     // 예외 발생 시 입력 form으로 forwarding
            request.setAttribute("creationFailed", true);
            request.setAttribute("exception", e);
            return "/board/postWrite.jsp";
        }
    }
}