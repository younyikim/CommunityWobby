package controller.club;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import service.CommunityManager;
import service.UserManager;
import service.dto.ClubDTO;

public class OutClubController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CommunityManager commManager = CommunityManager.getInstance();
        HttpSession session = request.getSession(); 
        request.setCharacterEncoding("UTF-8");
        
        String clubId = request.getParameter("clubId");
        System.out.println(clubId);
		
        String userId = UserSessionUtils.getLoginUserId(session);
        
        commManager.decreaseMember(clubId);
		commManager.removeMember(userId, clubId);
		
		System.out.println("실행");
		
		return "redirect:/club/myClub";  
	}

}