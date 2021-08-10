package controller.club;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import service.CommunityManager;
import service.dto.ClubDTO;

public class ListMyClubController implements Controller {
	@SuppressWarnings("unchecked")
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommunityManager commManager = CommunityManager.getInstance();
		List<ClubDTO> clubList = new ArrayList<ClubDTO>();

		HttpSession session = request.getSession();
		String userId = UserSessionUtils.getLoginUserId(session);
		clubList = commManager.findUsersClubList(userId);
		System.out.println(userId);

		request.setAttribute("clubList", clubList);
		for (ClubDTO club : clubList) {
			System.out.print(club);
			for (String category : club.getCategory())
				System.out.println(category);
		}
		return "/club/myClub.jsp";
	}
}
