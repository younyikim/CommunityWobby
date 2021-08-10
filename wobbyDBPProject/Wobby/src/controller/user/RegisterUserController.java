package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import service.dto.UserDTO;
import service.ExistingUserException;
import service.UserManager;

public class RegisterUserController implements Controller { // 처
	private static final Logger log = LoggerFactory.getLogger(RegisterUserController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		String mbti = request.getParameter("IE") + request.getParameter("NS") + request.getParameter("TF")
				+ request.getParameter("PJ");

		String region = request.getParameter("region1") + request.getParameter("region2")
				+ request.getParameter("region3");

		String phone = request.getParameter("phone1") + "-" + request.getParameter("phone2") + "-"
				+ request.getParameter("phone3");

		System.out.print(mbti);

		String[] hobby = request.getParameterValues("btn");
		if (hobby != null) {
			for (int i = 0; i < hobby.length; i++) {
				System.out.println("선택한 취미 : " + hobby[i]);
			}
		}

		UserDTO user = new UserDTO(request.getParameter("userId"), request.getParameter("password"),
				request.getParameter("name"), request.getParameter("email"), phone, region, mbti,
				request.getParameter("nickname"));

		log.debug("Create User : {}", user);

		try {
			UserManager manager = UserManager.getInstance();
			manager.create(user, hobby);
			return "redirect:/user/login/form"; //
		} catch (ExistingUserException e) {
			request.setAttribute("ExistingUserException", true);
			request.setAttribute("exception", e);
			request.setAttribute("user", user);
			return "/user/signUp.jsp";
		}
	}
}