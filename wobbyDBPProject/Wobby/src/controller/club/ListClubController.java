package controller.club;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import service.CommunityManager;
import service.dto.ClubDTO;

public class ListClubController implements Controller {
    @SuppressWarnings("unchecked")
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
        List<ClubDTO> clubList = (List<ClubDTO>) request.getAttribute("clubList");      
        HttpSession session = request.getSession(); 
        
        if (clubList.equals(null)) { //null�ϰ��, �� �˻� / ��Ī�� �ƴҰ��
            CommunityManager commManager = CommunityManager.getInstance();
            String userId = UserSessionUtils.getLoginUserId(session);
            
            clubList = commManager.findClubList();
        }
        
		// commList ��ü�� request�� �����Ͽ� Ŀ�´�Ƽ ����Ʈ ȭ������ �̵�(forwarding)
		request.setAttribute("clubList", clubList);				
		return "/community/club/list.jsp";        
    }
}
