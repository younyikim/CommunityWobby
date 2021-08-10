package controller.club;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import service.CommunityManager;
import service.UserManager;
import service.dto.ClubDTO;

public class ViewClubHomeController implements Controller {
    @SuppressWarnings("unchecked")
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(); 
        String userId = UserSessionUtils.getLoginUserId(session);
        
        if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";     // login form 요청으로 redirect
        }
        
        
        CommunityManager commManager = CommunityManager.getInstance();
        
        UserManager userManager = UserManager.getInstance();
        List<ClubDTO> pClubList = new ArrayList<ClubDTO>();  
        List<ClubDTO> cClubList = new ArrayList<ClubDTO>(); 
        String[] userHobbyList = new String[3]; 
        
        //find PopularClubList
        pClubList = commManager.findPopularClubList();   
        request.setAttribute("pClubList", pClubList);  
        
        if(userId.equals("admin")) {
        	return "/club/clubHome.jsp";  
        }

        //find Same CategoryClubList
        System.out.println(userId); 
        userHobbyList = userManager.findUsersHobby(userId); 
        System.out.println(userHobbyList); 
        
      
        cClubList = commManager.findClubListbyCategory(userHobbyList[0], userHobbyList[1], userHobbyList[2]);   
        request.setAttribute("cClubList", cClubList);  
        return "/club/clubHome.jsp";        
    }
}