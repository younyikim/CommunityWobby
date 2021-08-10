package controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import service.CommunityManager;
import service.UserManager;
import service.dto.ClubDTO;

public class DeleteUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(DeleteUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String deleteId = UserSessionUtils.getLoginUserId(request.getSession());
    	
    	UserManager manager = UserManager.getInstance();	
		HttpSession session = request.getSession();	
	
		CommunityManager commManager = CommunityManager.getInstance();
	
		List<ClubDTO> clubList = new ArrayList<ClubDTO>();
		clubList = commManager.findChairsClubList(deleteId);
		
		int j = clubList.size();
		
		System.out.println(j);
		

		if(j == 0) {
		     manager.remove(deleteId);
			
		     session.removeAttribute(UserSessionUtils.USER_SESSION_KEY);
             session.invalidate();      // 로그인한 사용자는 이미 삭제됨
			 return "redirect:/home";		// logout 처리
		}
		else {
			request.setAttribute("deleteFailed", true);
			request.setAttribute("exception", "생성한 클럽이 존재하여 탈퇴할 수 없습니다. 생성한 클럽을 삭제해주세요.");
			return "/user/myPage";	          
        }
	}
}
