package persistence;

import persistence.dao.*;
import persistence.dao.impl.*;

public class DAOFactory {
	public BoardDAO getBoardDAO() {
		return new BoardDAOImpl();
	}
	
	public ClubDAO getClubDAO() {
		return new ClubDAOImpl();
	}
	
	public PostDAO getPostDAO() {
		return new PostDAOImpl();
	}
	
	public CommentDAO getCommentDAO() {
		return new CommentDAOImpl();
	}
	
	// StudentDAO 瑜� �쐞�븳 RDB �슜 DAO Implementation 媛앹껜瑜� 諛섑솚
		public UserDAO getUserDAO() {
			return new UserDAOImpl();		 
		}
		
		// DeptDAO 瑜� �쐞�븳 RDB �슜 DAO Implementation 媛앹껜瑜� 諛섑솚
		public MsgDAO getMsgDAO() {
			return new MsgDAOImpl();		
		}
		
		// ProfDAO 瑜� �쐞�븳 RDB �슜 DAO Implementation 媛앹껜瑜� 諛섑솚
		public ScrapBoxDAO getScrapBoxDAO() {
			return new ScrapBoxDAOImpl();		
		}
		
		public AlarmDAO getAlarmDAO() {
			return new AlarmDAOImpl();		
		}
		
}
