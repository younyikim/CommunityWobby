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
	    	    //ID ã��
	    	     if (request.getParameter("type").equals("findId")) {
                     System.out.println("find Id start");
                     request.setAttribute("type", "findId");
	    	         String name = request.getParameter("name"); //email�� idã��
	                 String email = request.getParameter("email"); //email�� idã��
	                 
	                 String userId = manager.findId(name, email);    //  �����id ���� �˻�
	                 request.setAttribute("userId", userId);
                     System.out.println("find Id Successed! " + userId + " in findInfoController");
	                      
	                 return "/user/findIdSuccess.jsp";   // ����� ���� ȭ������ �̵� (forwarding)
	    	     }

	             // PW ã��
                 if (request.getParameter("type").equals("findPw")) {
                     System.out.println("find Pw start");
                     request.setAttribute("type", "findPw");
                     String userId = request.getParameter("userId");
                     String name = request.getParameter("name");
                     String email = request.getParameter("email");
                     System.out.println(userId + " / " + name + " / " + email);
                     
                     manager.findPw(userId, name, email);    //  �����id ���� �˻�
                     request.setAttribute("userId", userId);
                     System.out.println("find Pw Successed! " + userId + " in findInfoController");
                          
                     return "/user/findPwSuccess.jsp";   // ����� ���� ȭ������ �̵� (forwarding)
                 }
		   }	    	    
		   catch (Exception e){
		       request.setAttribute("findInfoFailed", true);
		       request.setAttribute("exception", new IllegalStateException("������ ã�� �� �����ϴ�."));  
		   }
           return "/user/findUserInfoForm.jsp";
	 }
}
