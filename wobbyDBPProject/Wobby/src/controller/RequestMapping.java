package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.*;
import controller.alarm.*;
import controller.board.*;
import controller.club.*;
import controller.matching_searching.*;
import controller.message.*;
import controller.scrap.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
        mappings.put("/", new ForwardController("/index.jsp"));
        mappings.put("/home", new HomeController());
        mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp"));
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/delete", new DeleteUserController());
        mappings.put("/user/logout", new LogoutController());
        mappings.put("/user/findUserInfo", new FindUserInfoController());
        mappings.put("/user/updatePw", new UpdatePwController());
        mappings.put("/user/signUp/form", new ForwardController("/user/signUp.jsp"));
        mappings.put("/user/signUp", new RegisterUserController());
        mappings.put("/user/myPage/scrap/list", new ListScrapController());
        mappings.put("/user/myPage/scrap/delete", new DeleteScrapController());
        mappings.put("/user/myPage", new ViewMyPageController());
      
//        mappings.put("/user/register/form", new ForwardController("/user/registerForm.jsp"));
//        mappings.put("/user/register", new RegisterUserController());
//        mappings.put("/user/update", new UpdateClubController());
//        mappings.put("/user/updateForm", new UpdateClubController());
//        mappings.put("/user/delete", new DeleteClubController());
//       
//        mappings.put("/user/findUseInfo/findId", new FindUserInfoController());
//        mappings.put("/user/findUseInfo/findPw", new FindUserInfoController());
        
      //message
        mappings.put("/user/myPage/message/send/form", new ForwardController("/message/sendingMessage.jsp"));
        mappings.put("/user/myPage/message/send", new SendMessageController());
        mappings.put("/user/myPage/message/delete", new DeleteMessageController());
        mappings.put("/user/myPage/message/list", new ListReceivedMessageController());
        mappings.put("/user/myPage/message/SentMessagelist", new ListSentMessageController());

        //alarm
//        mappings.put("/user/myPage/Alarm/delete", new DeleteAlarmController());
//        mappings.put("/user/myPage/Alarm/list", new ListAlarmController());

        //scrap
//        mappings.put("/user/myPage/scrap/delete", new DeleteScrapController());
//       
      
        //matching & searching
        mappings.put("/matching", new MatchingController());
        mappings.put("/matching/page", new ForwardController("/matching/matching.jsp"));
        mappings.put("/search", new SearchController());

        //board
        mappings.put("/community/post/create/form", new ForwardController("/board/postWrite.jsp"));
        mappings.put("/community/post/create", new CreatePostController());
        mappings.put("/community/post/delete", new DeletePostController());
        mappings.put("/community/board", new ListPostController());
        mappings.put("/club/board", new ListClubPostController());
        mappings.put("/community/postList", new ListPostController());
        mappings.put("/community/post/update", new UpdatePostController());
        mappings.put("/community/post/updateForm", new UpdatePostController());
        mappings.put("/community/post", new ViewPostController());
        mappings.put("/community/post/scrap", new CreateScrapController());
        mappings.put("/community/comment/create", new CreateCommentController());
        mappings.put("/community/comment/delete", new DeleteCommentController());
        mappings.put("/community/comment/update", new UpdateCommentController());

        //club
        mappings.put("/club/myClub", new ListMyClubController());
        mappings.put("/club/clubHome", new ViewClubHomeController());
        mappings.put("/club/createClub/form", new ForwardController("/club/createClub.jsp"));
        mappings.put("/club/createClub", new CreateClubController());
        mappings.put("/club/manageMyClub", new ListMyClubDeleteController());
        mappings.put("/club/register", new RegisterClubController());
        mappings.put("/club/outClub", new OutClubController());
        mappings.put("/club/removeClub", new DeleteClubController());        

//        mappings.put("/admin/post", new ViewPostbyAdminController());
//        mappings.put("/admin/club/clubHome", new ViewClubbyAdminController());
        
        mappings.put("/user/myPage/alarm/delete", new DeleteAlarmController());
        mappings.put("/user/myPage/alarm/list", new ListAlarmController());

        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {   
        return mappings.get(uri);
    }
}