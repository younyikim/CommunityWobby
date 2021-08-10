package controller.matching_searching;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import service.UserNotFoundException;
import service.CommunityManager;
import service.UserManager;
import service.dto.ClubDTO;
import service.dto.UserDTO;

public class MatchingController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		// 로그인 여부 확인
		if (!UserSessionUtils.hasLogined(session)) {
			return "redirect:/user/login/form"; // login form 요청으로 redirect
		}
		String userId = UserSessionUtils.getLoginUserId(session);
		String type = request.getParameter("type");
		System.out.println(userId + " / " + type);

		CommunityManager commMan = CommunityManager.getInstance();
		UserManager userMan = UserManager.getInstance();
		UserDTO user = userMan.findUser(userId);

		if (type.equals("mbti")) {
			String mbti = user.getMbti();
			System.out.println(mbti);
			List<ClubDTO> clubList = null;
			clubList = commMan.findClubListbyMBTI(mbti); // 입력받은 keyword로 club 검색
			request.setAttribute("clubList", clubList);
			request.setAttribute("name", user.getName());
			request.setAttribute("matchingType", "MBTI");
			request.setAttribute("ResultKeyword", user.getMbti());

			return "/matching/matching_result.jsp";
		} else if (type.equals("region")) {
			String region = user.getRegion();
			System.out.println(region);
			List<ClubDTO> clubList = null;
			clubList = commMan.findClubListbyRegion(region); // 입력받은 keyword로 club 검색
			request.setAttribute("clubList", clubList);
			request.setAttribute("name", user.getName());
			request.setAttribute("matchingType", "REGION");
			request.setAttribute("ResultKeyword", user.getRegion());

			return "/matching/matching_result.jsp";
		} else {
			return "/matching.jsp";
		}
	}
}
