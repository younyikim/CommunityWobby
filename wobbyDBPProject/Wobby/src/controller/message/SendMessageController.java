package controller.message;

//import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;   
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import service.UserManager;
//import service.UserNotFoundException;
//import service.dto.UserDTO;
import service.dto.MsgDTO;
import service.dto.UserDTO;
public class SendMessageController implements Controller{
	 @Override
	    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
	    	
		String userId = request.getParameter("userId");	
		String content = request.getParameter("content");
	    request.setAttribute("userId", userId);	
	
	    MsgDTO msg = new MsgDTO(request.getParameter("rcverId"),
					 request.getParameter("content"),
					request.getParameter("title"));
	   
	    HttpSession session = request.getSession();
        session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);
        
    	session.setAttribute("userId", UserSessionUtils.getLoginUserId(request.getSession()));	
    	session.setAttribute("sendId", UserSessionUtils.getLoginUserId(request.getSession()));	
    	
	    System.out.println(content);
		try {
			UserManager manager = UserManager.getInstance();
			manager.sendMessage(msg);
			return "redirect:/user/myPage/message/SentMessagelist";	
			
		} catch (Exception e) {
			
         request.setAttribute("Failed", true);
		 request.setAttribute("exception", e);
         return "/user/sendingMessage.jsp";			
		}	
	 }
}
