package controller.message;

//import java.sql.SQLException;
import java.util.ArrayList; 
import java.util.List;

import javax.servlet.http.HttpServletRequest;   
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import service.UserManager;
//import service.UserNotFoundException;
//import service.dto.UserDTO;
import service.dto.MsgDTO;

public class ListReceivedMessageController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	System.out.println("에효");
    	UserManager userManager = UserManager.getInstance();
        List<MsgDTO> msgList = new ArrayList<MsgDTO>();  
        
        HttpSession session = request.getSession(); 
        String userId = UserSessionUtils.getLoginUserId(session);
        
        msgList = userManager.findUsersReceivedMessage(userId);
        System.out.println();
        System.out.println(userId); 
        
        request.setAttribute("msgList", msgList);   
        for(MsgDTO msg : msgList) {
            System.out.print(msg.getContent());            
        }
        return "/user/messageBox.jsp"; 
    	/*
		UserManager manager = UserManager.getInstance();
		List<Community> commList = manager.findCommunityList();

		request.setAttribute("msgList", msgList);				
		return "/user/messageBox.jsp";  
		*/
    }
}

