package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import service.UserManager;

public class UpdatePwController implements Controller {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserManager manager = UserManager.getInstance();
          try {
              String userId = request.getParameter("userId");
              System.out.println(userId);
              String inputPw = request.getParameter("inputPw");
              String inputPwCheck = request.getParameter("inputPwCheck");
              
              if(!inputPw.equals(inputPwCheck)) {
                  request.setAttribute("userId", userId);
                  request.setAttribute("updatePwFailed", true);
                  request.setAttribute("exception", new IllegalStateException("��й�ȣ Ȯ�ΰ� ��ġ���� �ʽ��ϴ�."));

                  return "/user/findPwSuccess.jsp";
              }
              
              manager.updatePw(userId, inputPw);    //  �����id ���� �˻�
              request.setAttribute("pwUpdated", true);
              return "/user/login/form";
          }                
          catch (Exception e){
              request.setAttribute("updatePwFailed", true);
              request.setAttribute("exception", new IllegalStateException("��й�ȣ ���濡 ������ �߻��߽��ϴ�."));
          }
          return "/user/findPwSuccess.jsp";
    }
}
