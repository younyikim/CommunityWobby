package controller.user;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import service.UserManager;

public class LoginController implements Controller {
    
     @Override
        public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
            String userId = request.getParameter("userId");
            String password = request.getParameter("password");
            String saveId = request.getParameter("idSaveCheck");
            
            try {
                if(saveId != null) {
                    Cookie cookie = new Cookie("savedId", userId);
                    response.addCookie(cookie);
                }
                UserManager manager = UserManager.getInstance();
                
                //System.out.println(userId + " / " + password);
                boolean result = manager.login(userId, password);
                System.out.println(result);
                
                HttpSession session = request.getSession();
                session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);
                
            	session.setAttribute("curUserId", UserSessionUtils.getLoginUserId(request.getSession()));		
            	
            	System.out.println(UserSessionUtils.getLoginUserId(request.getSession()));
            	return "/user/wobbyHome_login.jsp";            
            } catch (Exception e) {
                request.setAttribute("loginFailed", true);
                request.setAttribute("exception", new IllegalStateException("媛��엯�븯吏� �븡�� �븘�씠�뵒�씠嫄곕굹, �옒紐삳맂 鍮꾨�踰덊샇�엯�땲�떎."));
                System.out.print("falied");
                return "/user/loginForm.jsp";           
            }   
        }
}