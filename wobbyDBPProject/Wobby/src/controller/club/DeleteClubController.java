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
import service.dto.PostDTO;

public class DeleteClubController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {   
        HttpSession session = request.getSession(); 
        CommunityManager commManager = CommunityManager.getInstance();     
        
        String clubId = request.getParameter("clubId");
        
        ClubDTO club = commManager.findClub(clubId);
        
        
        int numofmem = club.getNumOfMembers();
        System.out.print("멤버수" +numofmem);
       
        
        if(numofmem > 1)
        {
        	request.setAttribute("deleteFailed", true);
			request.setAttribute("exception", "멤버가 있는 클럽은 삭제할 수 없습니다.");
			return "/club/manageMyClub";	 
        }
        else {
        int boardId = commManager.findBoardId(clubId);
        
        List<PostDTO> postList = new ArrayList<PostDTO>();

        postList = commManager.findPostList(boardId);
        
        for(int i = 0; i < postList.size(); i++) {
        	int postId = postList.get(i).getPostId();
        	int j = commManager.deleteScrapBypostId(postId);   
        	commManager.removePost(postId);
        }
  
         int i = commManager.removeClub(clubId);   
         
         System.out.print(clubId + "클럽 삭제결과?  "+i+"입니다.");
         
         return "redirect:/club/manageMyClub";  
        }
    }
}
