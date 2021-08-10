package controller.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import service.CommunityManager;
import service.UserManager;
import service.dto.AlarmDTO;
import service.dto.PostDTO;
import service.dto.ScrapBoxDTO;

public class ViewMyPageController  implements Controller {

	  @Override
	    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception { 
	        HttpSession session = request.getSession(); 
	        String userId = UserSessionUtils.getLoginUserId(session);			
	   
	    	if (!UserSessionUtils.hasLogined(request.getSession())) {
	            return "redirect:/user/login/form";		
	        }
			UserManager manager = UserManager.getInstance();
			CommunityManager commManager = CommunityManager.getInstance();
			
			//alarm
            System.out.println("S");
            List<AlarmDTO> alarmList = new ArrayList<AlarmDTO>();
            List<AlarmDTO> myPageAlarmList = new ArrayList<AlarmDTO>();
            alarmList = commManager.getAlarmList(userId);
            int alarmNum = alarmList.size();
            System.out.println(alarmNum);
            for (int i = 0; i < 3 && i < alarmNum; i++) {
                System.out.println(alarmNum);
                AlarmDTO alarm = new AlarmDTO(alarmList.get(i).getAlarmNo(),
                        alarmList.get(i).getCommentId(),
                        alarmList.get(i).getPostId());
                System.out.println(alarm);
                AlarmDTO resultAlarm = commManager.findAlarmInfo(alarm);
                if(resultAlarm.getTitle().length() > 5) {
                    String resultStr = resultAlarm.getTitle().substring(0, 5) + "...";
                    resultAlarm.setTitle(resultStr);
                }
                if(resultAlarm.getCommentContent().length() > 5) {
                    String resultStr = resultAlarm.getCommentContent().substring(0, 5) + "...";
                    resultAlarm.setCommentContent(resultStr);
                }
                myPageAlarmList.add(resultAlarm);
            }
            request.setAttribute("alarmList", myPageAlarmList);
            request.setAttribute("alarmNum", alarmNum);
            
            
			//scrap
	        List<ScrapBoxDTO> scrapList = new ArrayList<ScrapBoxDTO>();
	        List<ScrapBoxDTO> myPageScrapList = new ArrayList<ScrapBoxDTO>();
	        System.out.println(userId);
			scrapList = commManager.getScrapList(userId);
			int scrapNum = scrapList.size();
			for (int i = 0; i < 3 && i < scrapNum; i++) {
                PostDTO post = commManager.findPostInfo(scrapList.get(i).getPostId());
			    ScrapBoxDTO scrapPost = new ScrapBoxDTO(post.getTitle(), post.getBoardName());
			    myPageScrapList.add(scrapPost);
	            System.out.println(myPageScrapList.get(i).getTitle());
			}
            request.setAttribute("scrapList", myPageScrapList);
            request.setAttribute("scrapNum", scrapNum);

	    	request.setAttribute("userId", userId);					
			return "/user/myPage.jsp";				
	    }
}
