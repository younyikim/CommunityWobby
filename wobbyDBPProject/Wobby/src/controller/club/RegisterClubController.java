package controller.club;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import service.CommunityManager;
import service.ExistingUserException;
import service.UserManager;
import service.dto.ClubDTO;
import service.dto.PostDTO;

public class RegisterClubController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		CommunityManager commManager = CommunityManager.getInstance();

		String clubId = request.getParameter("clubId");
		String userId = UserSessionUtils.getLoginUserId(session);
		String chairId = commManager.getChairId(clubId);
		System.out.print(chairId + "만든사람");
		System.out.print(userId + "은 나!!");

		List<ClubDTO> clubList = new ArrayList<ClubDTO>();
		clubList = commManager.findUsersClubList(userId);

		ClubDTO club = commManager.findClub(clubId);

		int n = club.getNumOfMembers();
		int m = club.getMaxNumMembers();


		if(m - n < 6) {
			commManager.setClubPopularity(clubId);
			System.out.print("클럽의 인기도"+club.getPopularity());
		}
			if (n == m) {
			request.setAttribute("memberFulled", true);
			request.setAttribute("exception", "정원이 가득찬 클럽입니다.");
			return "/club/clubHome";
		} else {
			for (int i = 0; i < clubList.size(); i++) {
				if (clubId.equals(clubList.get(i).getClubId())) {
					request.setAttribute("registerFailed", true);
					request.setAttribute("exception", "이미 가입한 클럽입니다.");
					return "/club/clubHome";
				}
				System.out.print("가입 불가.");
			}

			if (userId.equals(chairId)) {
				request.setAttribute("registerFailed", true);
				request.setAttribute("exception", "자신이 생성한 클럽은 가입할 수 없습니다.");
				return "/club/clubHome";
			} else {
				commManager.increaseMember(clubId);
				commManager.newMember(userId, clubId);
				commManager.updateClubMbti(clubId);
				return "redirect:/club/myClub";
			}
		}
	}
}