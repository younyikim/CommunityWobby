package controller.user;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import service.UserManager;

public class FindUserInfoController implements Controller {

	 @Override
	    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
         UserManager manager = UserManager.getInstance();
		   try {
	    	    //ID 찾기
	    	     if (request.getParameter("type").equals("findId")) {
                     System.out.println("find Id start");
                     request.setAttribute("type", "findId");
	    	         String name = request.getParameter("name"); //email로 id찾기
	                 String email = request.getParameter("email"); //email로 id찾기
	                 
	                 String userId = manager.findId(name, email);    //  사용자id 정보 검색
	                 request.setAttribute("userId", userId);
                     System.out.println("find Id Successed! " + userId + " in findInfoController");
	                      
	                 return "/user/findIdSuccess.jsp";   // 사용자 보기 화면으로 이동 (forwarding)
	    	     }

	             // PW 찾기
                 if (request.getParameter("type").equals("findPw")) {
                     System.out.println("find Pw start");
                     request.setAttribute("type", "findPw");
                     String userId = request.getParameter("userId");
                     String name = request.getParameter("name");
                     String email = request.getParameter("email");
                     System.out.println(userId + " / " + name + " / " + email);
                     
                     manager.findPw(userId, name, email);    //  사용자id 정보 검색
                     request.setAttribute("userId", userId);
                     System.out.println("find Pw Successed! " + userId + " in findInfoController");
                          
                     return "/user/findPwSuccess.jsp";   // 사용자 보기 화면으로 이동 (forwarding)
                 }
		   }	    	    
		   catch (Exception e){
		       request.setAttribute("findInfoFailed", true);
		       request.setAttribute("exception", new IllegalStateException("정보를 찾을 수 없습니다."));  
		   }
           return "/user/findUserInfoForm.jsp";
	 }
}
