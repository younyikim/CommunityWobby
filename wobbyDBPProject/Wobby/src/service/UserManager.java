package service;

/**
 * 커占승댐옙티 占쏙옙占쏙옙 API占쏙옙 占쏙옙占쏙옙求占� 占쏙옙占쏙옙占쌘듸옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占싹곤옙 占실댐옙 클占쏙옙占쏙옙.
 * UserDAO/CommunityDAO占쏙옙 占싱울옙占싹울옙 占쏙옙占쏙옙占싶븝옙占싱쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쌜억옙占쏙옙 占쏙옙占쏙옙占싹듸옙占쏙옙 占싹몌옙,
 * 占쏙옙占쏙옙占싶븝옙占싱쏙옙占쏙옙 占쏙옙占쏙옙占싶듸옙占쏙옙 占싱울옙占싹울옙 占쏙옙占쏙옙占싹쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싹댐옙 占쏙옙占쏙옙占쏙옙 占싼댐옙.
 * 占쏙옙占쏙옙占싹쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙荑∽옙占� 占쏙옙占쏙옙占싹쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싹댐옙 클占쏙옙占쏙옙占쏙옙 
 * 占쏙옙占쏙옙占쏙옙 占쏙옙 占쏙옙 占쌍댐옙.
 */

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.UserManager;
import persistence.dao.UserDAO;
import persistence.dao.impl.MsgDAOImpl;
import persistence.dao.impl.UserDAOImpl;
import persistence.dao.MsgDAO;
import persistence.dao.ScrapBoxDAO;
import persistence.dao.ClubDAO; //regionmathcing
import service.dto.UserDTO;
import service.dto.MsgDTO;
import service.dto.PostDTO;
import service.dto.ScrapBoxDTO;
import service.dto.ClubDTO;


public class UserManager {
	private static UserManager userMan = new UserManager();
	private UserDAOImpl userDAOImpl = new UserDAOImpl();
    private MsgDAOImpl msgDAOImpl = new MsgDAOImpl();
	
	public UserManager() {
		super();
	}

	public static UserManager getInstance() {
		return userMan;
	}

	public boolean login(String userId, String password)
			throws SQLException, UserNotFoundException, PasswordMismatchException {
		System.out.println("UserManager login call success");
		UserDTO user = findUser(userId);

		if (!user.matchPassword(password)) {
			System.out.println("matchPassword success");
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다");
		}

		return true;
	}

	public int create(UserDTO user, String[] hobby) throws SQLException, ExistingUserException {
		if (userDAOImpl.existingUser(user.getUserId()) == true) {
			throw new ExistingUserException(user.getUserId() + "는 있는 아이디입니다.");
		}
		return userDAOImpl.create(user,hobby);
	}

	public String[] findUsersHobby(String userId) throws SQLException, UserNotFoundException {
		String[] resultList = new String[3];
		List<String> userHobbyList = new ArrayList<String>();
		userHobbyList = userDAOImpl.findUsersHobby(userId);
		if(userId.equals("admin"))
			return null;
		else {
			resultList[0] = userHobbyList.get(0);
			resultList[1] = userHobbyList.get(1);
			resultList[2] = userHobbyList.get(2);
		return resultList;
		}
	}

	public int remove(String userId) throws SQLException, UserNotFoundException {
	       return userDAOImpl.remove(userId); }

	public UserDTO findUser(String userId) throws SQLException, UserNotFoundException {
		System.out.println("usermanager finduser");
		UserDTO user = userDAOImpl.findUser(userId);

		if (user == null) {
			throw new UserNotFoundException("사용자(" + userId + ")를 찾을 수 없습니다.");
		}
		return user;
	}

	public List<UserDTO> findUserList() throws SQLException {
		return userDAOImpl.findUserList();
	}

	public String findId(String name, String email) throws SQLException, UserNotFoundException {
		String userId = null;
		userId = userDAOImpl.findId(email);

		if (userId == null) {
			throw new UserNotFoundException("이메일 (" + email + ")이 존재하지 않습니다.");
		} /*
			 * else if (!user.getName().equals(name)) { throw new
			 * UserNotFoundException("올바르지 않은 정보입니다."); }
			 */
		return userId;
	}

	public String findPw(String userId, String inputName, String inputEmail)
			throws SQLException, UserNotFoundException {
		UserDTO user = null;
		user = userDAOImpl.findUser(userId);

		if (user == null) {
			throw new UserNotFoundException("아이디 (" + userId + ")가 존재하지 않습니다.");
		} else {
			String name = user.getName();
			String email = user.getEmail();
			System.out.println(userId + " / " + inputName + " / " + inputEmail);
			System.out.println(userId + " / " + name + " / " + email);

			if (!name.equals(inputName) || !email.equals(inputEmail)) {
				throw new UserNotFoundException("정보가 일치하지 않습니다.");
			}
		}
		return user.getUserId();
	}

	public String updatePw(String userId, String password) throws SQLException, UserNotFoundException {
		UserDTO user = null;
		user = userDAOImpl.findUser(userId);

		if (user == null) {
			throw new UserNotFoundException("아이디 (" + userId + "가 존재하지 않습니다.");
		}
		userDAOImpl.updatePw(userId, password);
		return user.getUserId();
	}
	


    //------------------쪽지 ------------insertMessage(), receivedMessage() 
        public int sendMessage(MsgDTO msg) throws SQLException {
            //MsgDTO msg = msgDAOImpl.sendMsg(msg);
            //return 0;
            System.out.println("UserManager sendMessage");
            return msgDAOImpl.sendMsg(msg);
        }
        
        public List<MsgDTO> findUsersReceivedMessage(String userId) throws SQLException {

            /*List<String> msgList = new ArrayList<String>();
            msgList = msgDAOImpl.findUsersClubIDList(userId);
                
            return ReceiveMsgList(msgList);*/
                
            System.out.println("UserManager findUsersReceivedMessage");
            return msgDAOImpl.ReceiveMsgList(userId);
        }
        
        public List<MsgDTO> findUsersSentMessage(String userId) throws SQLException{
            // TODO Auto-generated method stub
            System.out.println("UserManager findUsersSentMessage");
            return msgDAOImpl.SendMsgList(userId);
        }
        
        public int deleteMsg(int selectedMessage) throws SQLException {
            // TODO Auto-generated method stub
            System.out.println("UserManager deleteMsg");
            return msgDAOImpl.deleteMsg(selectedMessage);
        }
	//
	public int outClub(String userId, int clubId) {
		UserDTO user = userDAOImpl.findUser(userId);
		return 0;
	}

	public int registerClub(String userId, int clubId) {
		UserDTO user = userDAOImpl.findUser(userId);
		return 0;
	}

	public UserDAO getUserDAO() {
		return this.userDAOImpl;
	}

}