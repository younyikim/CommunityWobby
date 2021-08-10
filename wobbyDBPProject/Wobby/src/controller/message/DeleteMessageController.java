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


public class DeleteMessageController implements Controller{
	 @Override
	    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
	    
		 UserManager manager = UserManager.getInstance();
		 int selectedMessage = Integer.parseInt(request.getParameter("msgNo"));
		
		try {
			request.setAttribute("selectedMessage", selectedMessage);
			manager.deleteMsg(selectedMessage);
			return "redirect:/user/myPage/message/list";			
		} catch (Exception e) {
			
         request.setAttribute("Failed", true);
		 request.setAttribute("exception", e);
         return "/user/myPage/message/list.jsp";			
		}	
	 }
}
