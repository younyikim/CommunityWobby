package controller.scrap;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import service.CommunityManager;
import service.dto.ClubDTO;
import service.dto.PostDTO;
import service.dto.ScrapBoxDTO;

public class ListScrapController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {   
        HttpSession session = request.getSession(); 
        String userId = UserSessionUtils.getLoginUserId(session);
        
        if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";     // login form �슂泥��쑝濡� redirect
        }
        CommunityManager commManager = CommunityManager.getInstance();
        List<ScrapBoxDTO> scrapList = new ArrayList<ScrapBoxDTO>();
        List<ScrapBoxDTO> postListofPage = new ArrayList<ScrapBoxDTO>();
        int page = 1;
        
       try {
            scrapList = commManager.getScrapList(userId);
            request.setAttribute("lastPage", (scrapList.size()/15 + 1));
            
            if(request.getParameter("page") != null) 
                page = Integer.getInteger(request.getParameter("page"));
            
            //MAKE SMALL SCRAP LIST
            for(int i = (page - 1) * 15; i < (page * 15); i++) {
                if(i > scrapList.size() - 1) break;
                PostDTO post = commManager.findPostInfo(scrapList.get(i).getPostId());
                ScrapBoxDTO scrap = new ScrapBoxDTO(scrapList.get(i).getScrapNo(), 
                                post.getPostId(),
                                post.getUserId(),
                                post.getNickname(),
                                post.getPostDate(),
                                post.getTitle(),
                                post.getBoardId(),
                                post.getBoardName());
                postListofPage.add(scrap);
                //scrapListofPage.add(scrapList.get(i));
            }
            
            request.setAttribute("page", page);
            request.setAttribute("scrapList", postListofPage);
            request.setAttribute("scrapNum", scrapList.size());
            return "/user/scrapList.jsp";

        } catch (Exception e) {     // �뜝�룞�삕�뜝�룞�삕 �뜝�뙥�궪�삕 �뜝�룞�삕 �뜝�뙃琉꾩삕 form�뜝�룞�삕�뜝�룞�삕 forwarding
            request.setAttribute("scrapListFailed", true);
            request.setAttribute("exception", e);
            return "/user/myPage.jsp";
        }
    }
}

