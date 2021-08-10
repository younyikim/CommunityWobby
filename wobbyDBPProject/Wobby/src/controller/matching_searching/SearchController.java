package controller.matching_searching;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import service.CommunityManager;
import service.UserManager;
import service.dto.ClubDTO;
import service.dto.PostDTO;
import service.dto.UserDTO;

public class SearchController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        // 로그인 여부 확인
        if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form"; // login form 요청으로 redirect
        }
        try {
            String userId = UserSessionUtils.getLoginUserId(session);
            String searchWord = request.getParameter("keyword");
            request.setAttribute("searchWord", searchWord);
            System.out.println(userId + " / " + searchWord);

            if (searchWord.equals("")) {
                request.setAttribute("clubList", null);
                request.setAttribute("postList", null);

                return "/search/search.jsp";
            }

            CommunityManager commMan = CommunityManager.getInstance();
            UserManager userMan = UserManager.getInstance();
            UserDTO user = userMan.findUser(userId);

            // searchPost
            List<PostDTO> postList = null;
            postList = commMan.searchPostList(searchWord); // 입력받은 keyword로 club 검색
            if (postList.size() == 0)
                postList = null;
            request.setAttribute("postList", postList);

            // searchClub
            List<ClubDTO> clubList = null;
            clubList = commMan.searchClubList(searchWord); // 입력받은 keyword로 club 검색
            if (clubList.size() == 0)
                clubList = null;
            request.setAttribute("clubList", clubList);

            return "/search/search.jsp";
        } catch (Exception e) {
            request.setAttribute("searchFailed", true);
            request.setAttribute("exception", e);
            System.out.print("falied");
            return "/search/search.jsp";
        }
    }
}