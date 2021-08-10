package controller.club;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.RegisterUserController;
import controller.user.UserSessionUtils;
import service.CommunityManager;
import service.ExistingClubException;
import service.dto.BoardDTO;
import service.dto.ClubDTO;

public class CreateClubController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(RegisterUserController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String userId = UserSessionUtils.getLoginUserId(session);

		request.setCharacterEncoding("UTF-8");

		String region = request.getParameter("region1") + request.getParameter("region2")
				+ request.getParameter("region3");

		String[] hobby = request.getParameterValues("btn");
		if (hobby != null) {
			for (int i = 0; i < hobby.length; i++) {
				System.out.println("선택한 클럽 : " + hobby[i]);
			}
		}

		ClubDTO club = new ClubDTO(
				request.getParameter("clubId"), 
				request.getParameter("clubName"), 
				region,
				Integer.parseInt(request.getParameter("maxOfmembers")), 
				userId);

		log.debug("Create club : {}", club);
		try {
			CommunityManager commManager = CommunityManager.getInstance();
			System.out.println("클럽 만들기 ");

			int result = commManager.createClub(club, hobby);
			//commManager.increaseMember(request.getParameter("clubId"));
			System.out.println(result);

			if (result > 0) {
				BoardDTO board = new BoardDTO(club.getClubId(), club.getName());
				commManager.createBoard(board);
			}

			return "redirect:/club/clubHome";
		} catch (ExistingClubException e) {
			request.setAttribute("creationFailed", true);
			request.setAttribute("exception", new IllegalStateException("만들기 실패"));
			System.out.println("실패.");
			return "/club/createClub.jsp";
		}
	}
}
