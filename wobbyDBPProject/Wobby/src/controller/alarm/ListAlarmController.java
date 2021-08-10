package controller.alarm;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import service.CommunityManager;
import service.dto.ClubDTO;
import service.dto.CommentDTO;
import service.dto.PostDTO;
import service.dto.AlarmDTO;

public class ListAlarmController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {   
        HttpSession session = request.getSession(); 
        String userId = UserSessionUtils.getLoginUserId(session);
        
        if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";  
        }
        CommunityManager commManager = CommunityManager.getInstance();
        List<AlarmDTO> alarmList = new ArrayList<AlarmDTO>();
        List<AlarmDTO> alarmListofPage = new ArrayList<AlarmDTO>();
        int page = 1;
        
       try {
           alarmList = commManager.getAlarmList(userId);
            request.setAttribute("lastPage", (alarmList.size()/15 + 1));
            
            if(request.getParameter("page") != null) 
                page = Integer.getInteger(request.getParameter("page"));
            
            //MAKE SMALL SCRAP LIST
            for(int i = (page - 1) * 15; i < (page * 15); i++) {
                if(i > alarmList.size() - 1) break;
                AlarmDTO alarm = new AlarmDTO(alarmList.get(i).getAlarmNo(),
                                alarmList.get(i).getCommentId(),
                                alarmList.get(i).getPostId());
                AlarmDTO resultAlarm = commManager.findAlarmInfo(alarm);
                if(resultAlarm.getTitle().length() > 8) {
                    String resultStr = resultAlarm.getTitle().substring(0, 8) + "...";
                    resultAlarm.setTitle(resultStr);
                }
                if(resultAlarm.getCommentContent().length() > 8) {
                    String resultStr = resultAlarm.getCommentContent().substring(0, 8) + "...";
                    resultAlarm.setCommentContent(resultStr);
                }
                alarmListofPage.add(resultAlarm);
            }

            request.setAttribute("page", page);
            request.setAttribute("alarmList", alarmListofPage);
            request.setAttribute("alarmNum", alarmList.size());
            return "/user/alarmList.jsp";

        } catch (Exception e) {  
            request.setAttribute("alarmListFailed", true);
            request.setAttribute("exception", e);
            return "/user/myPage.jsp";
        }
    }
}

