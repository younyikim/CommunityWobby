package controller.club;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import service.CommunityManager;
import service.dto.ClubDTO;

public class ViewClubbyAdminController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	
    	ClubDTO club = null;
		CommunityManager commManager = CommunityManager.getInstance();
		
		String clubId = request.getParameter("clubId");
		club = commManager.findClub(clubId);	
		request.setAttribute("club", club);			
		return "/admin/club/clubHome.jsp?clubId=" + clubId;
    }
}
