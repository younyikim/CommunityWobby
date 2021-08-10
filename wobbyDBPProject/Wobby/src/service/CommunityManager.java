package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.BoardNotFoundException;
import service.PostNotFoundException;
import service.dto.ClubDTO;
import service.dto.CommentDTO;
import service.dto.AlarmDTO;
import service.dto.BoardDTO;
import service.dto.PostDTO;
import service.dto.ScrapBoxDTO;
import persistence.dao.ClubDAO;
import persistence.dao.BoardDAO;
import persistence.dao.PostDAO;
import persistence.dao.impl.AlarmDAOImpl;
import persistence.dao.impl.BoardDAOImpl;
import persistence.dao.impl.ClubDAOImpl;
import persistence.dao.impl.CommentDAOImpl;
//import service.dto.UserDTO;
//import service.dto.CommunityDTO;
//import persistence.dao.UserDAO;
//import persistence.dao.CommunityDAO;
import persistence.dao.impl.PostDAOImpl;
import persistence.dao.impl.ScrapBoxDAOImpl;

public class CommunityManager {
	private static CommunityManager commMan = new CommunityManager();
//    private UserDAO userDAO;
//    private CommunityDAO commDAO;
	private ClubDAOImpl clubDAOImpl = new ClubDAOImpl();
	private BoardDAOImpl boardDAOImpl = new BoardDAOImpl();
	private PostDAOImpl postDAOImpl = new PostDAOImpl();
	private CommentDAOImpl commentDAOImpl = new CommentDAOImpl();
	private ScrapBoxDAOImpl scrapboxDAOImpl = new ScrapBoxDAOImpl();
	private AlarmDAOImpl alarmDAOImpl = new AlarmDAOImpl();
//    private UserAnalysis userAanlysis;

	/*
	 * private CommunityManager() { try { userDAO = new UserDAO(); commDAO = new
	 * CommunityDAO(); userAanlysis = new UserAnalysis(userDAO); } catch (Exception
	 * e) { e.printStackTrace(); } }
	 */

	public static CommunityManager getInstance() {
		return commMan;
	}

	// -------------CLUB-------------

	// -------------CLUB-------------
	public ClubDTO findClub(String clubId) throws SQLException {
		return clubDAOImpl.findClub(clubId);
	}

	public List<ClubDTO> findClubList() throws SQLException {
		return clubDAOImpl.findClubList();
	}

	public int createClub(ClubDTO club, String[] hobby) throws SQLException, ExistingClubException {
		if (clubDAOImpl.existingClub(club.getName()) == true) {
			throw new ExistingClubException(club.getName() + "�씠誘� 議댁옱�븯�뒗 �겢�읇紐낆엯�땲�떎.");
		}
		return clubDAOImpl.createClub(club, hobby);
	}

	public List<ClubDTO> findChairsClubList(String chairId) throws SQLException {
		List<String> clubIdList = new ArrayList<String>();
		clubIdList = clubDAOImpl.findChairsClubIDList(chairId);

		return findClubListbyClubIdList(clubIdList);
	}

	public int setClubPopularity(String clubId) throws SQLException, ClubNotFoundException {
		if (findClub(clubId) == null) {
			throw new ClubNotFoundException(clubId + "를 찾을 수 없습니다.");
		}

		return clubDAOImpl.setClubPopularity(clubId);
	}

	public int newMember(String userId, String clubId) throws SQLException, ClubNotFoundException {
		if (findClub(clubId) == null) {
			throw new ClubNotFoundException(clubId + "를 찾을 수 없습니다.");
		}

		return clubDAOImpl.insertUser(userId, clubId);
	}

	public int removeMember(String userId, String clubId) throws SQLException, ClubNotFoundException {
		if (findClub(clubId) == null) {
            throw new ClubNotFoundException(clubId + "를 찾을 수 없습니다.");
		}

		return clubDAOImpl.removeUser(userId, clubId);
	}

	public int removeClub(String clubId) throws SQLException, ClubNotFoundException {
		if (findClub(clubId) == null) {
			throw new ClubNotFoundException(clubId + "찾을 수 없습니다.");
		}
		return clubDAOImpl.delete(clubId);
	}

	public int updateClubMbti(String clubId) throws SQLException {
		String mbti = clubDAOImpl.findMaxOfMbtiInClub(clubId);
		return clubDAOImpl.updateClubMbti(clubId, mbti);
	}

//    public int increaseClubMemberCnt(int clubId) throws SQLException{
//          return clubDAOImpl.increaseClubMemberCnt(clubId);
//    }
//   

	public List<ClubDTO> findClubListbyClubIdList(List<String> clubIdList) throws SQLException {
		List<ClubDTO> clubList = new ArrayList<ClubDTO>();
		for (String clubId : clubIdList) {
			ClubDTO club = findClub(clubId);
			club.setCategory(clubDAOImpl.findClubCategory(clubId));
			clubList.add(club);
		}
		return clubList;
	}

	public List<ClubDTO> findClubListbyMBTI(String mbti) throws SQLException {
		List<String> clubIdList = new ArrayList<String>();
		clubIdList = clubDAOImpl.findClubListbyMBTI(mbti);

		return findClubListbyClubIdList(clubIdList);
	}

	public List<ClubDTO> findClubListbyRegion(String region) throws SQLException {
		List<String> clubIdList = new ArrayList<String>();
		clubIdList = clubDAOImpl.findClubListbyRegion(region);

		return findClubListbyClubIdList(clubIdList);
	}

	public List<ClubDTO> findUsersClubList(String userId) throws SQLException {
		List<String> clubIdList = new ArrayList<String>();
		clubIdList = clubDAOImpl.findUsersClubIDList(userId);

		return findClubListbyClubIdList(clubIdList);
	}

	public List<ClubDTO> findPopularClubList() throws SQLException {
		List<String> clubIdList = new ArrayList<String>();
		clubIdList = clubDAOImpl.findPopularClubList();

		return findClubListbyClubIdList(clubIdList);
	}

	public List<ClubDTO> findClubListbyCategory(String first, String second, String third) throws SQLException {
		List<String> clubIdList = new ArrayList<String>();
		clubIdList = clubDAOImpl.findClubListbyCategory(first, second, third);

		return findClubListbyClubIdList(clubIdList);
	}

	public List<ClubDTO> searchClubList(String keyword) throws SQLException {
		List<ClubDTO> clubList = new ArrayList<ClubDTO>();
		List<String> clubIdList = new ArrayList<String>();
		clubIdList = clubDAOImpl.searchClubList(keyword);
		for (String clubId : clubIdList) {
			ClubDTO club = findClub(clubId);
			club.setCategory(clubDAOImpl.findClubCategory(clubId));
			clubList.add(club);
		}
		return clubList;
	}

	public String getChairId(String clubId) throws SQLException {
		return clubDAOImpl.getChairId(clubId);
	}

	public int increaseMember(String clubId) {
		return clubDAOImpl.increaseClubMemberCnt(clubId);
	}

	public int decreaseMember(String clubId) {
		return clubDAOImpl.decreaseClubMemberCnt(clubId);
	}

	// -------------BOARD-------------
	public BoardDTO findBoard(int boardId) throws SQLException {
		return boardDAOImpl.findBoard(boardId);
	}

	public int deleteBoard(int boardId) throws SQLException {
		return boardDAOImpl.delete(boardId);
	}

	public BoardDTO createBoard(BoardDTO board) throws SQLException {
		return boardDAOImpl.create(board);
	}

	public int findBoardId(String clubId) throws SQLException, PostNotFoundException {
		if (findClub(clubId) == null) {
			throw new PostNotFoundException(clubId + "없습니다.");
		}
		return boardDAOImpl.findBoardId(clubId);
	}

	// -------------POST-------------
	public PostDTO findPost(int postId) throws SQLException {
		return postDAOImpl.findPost(postId);
	}

	public int create(PostDTO post, int boardId) throws SQLException, BoardNotFoundException {
		if (findBoard(boardId) == null) {
			throw new BoardNotFoundException(boardId + "를 찾을 수 없습니다.");
		}
		int result = postDAOImpl.create(post, boardId);
		return post.getPostId();
	}

	public int removePost(int postId) throws SQLException, PostNotFoundException {
		if (findPost(postId) == null) {
			throw new PostNotFoundException(postId + "를 찾을 수 없습니다.");
		}
		return postDAOImpl.delete(postId);
	}

	public int update(PostDTO post) throws SQLException, PostNotFoundException {
		return postDAOImpl.update(post);
	}

	public int increasePostViewCnt(int postId) throws SQLException, PostNotFoundException {
		if (findPost(postId) == null) {
			throw new PostNotFoundException(postId + "를 찾을 수 없습니다.");
		}
		return postDAOImpl.increasePostViewCnt(postId);
	}

	public List<PostDTO> searchPostList(String keyword) throws SQLException {
		List<PostDTO> postList = new ArrayList<PostDTO>();
		postList = postDAOImpl.searchPostList(keyword);

		return setBoardNamebyPostList(postList);
	}

	public List<PostDTO> setBoardNamebyPostList(List<PostDTO> postList) throws SQLException {
		List<PostDTO> resultList = new ArrayList<PostDTO>();
		for (PostDTO post : postList) {
			BoardDTO board = findBoard(post.getBoardId());

			String hobbyId = board.getHobbyId();
			String clubId = board.getClubId();
			if (hobbyId != null) {
				post.setBoardName(board.getName());
			}
			if (clubId != null) {
				ClubDTO club = findClub(clubId);
				post.setBoardName(club.getName());
			}
			resultList.add(post);
		}
		return resultList;
	}

	public List<PostDTO> findPostList(int boardId) throws SQLException {
		System.out.println("comm manager : " + boardId);
//    	List<PostDTO> test = postDAOImpl.findPostList(boardId);
		return postDAOImpl.findPostList(boardId);
	}

	public List<PostDTO> findPopularPostList(int boardId) throws SQLException {
		return postDAOImpl.findPopularPostList(boardId);
	}

	// -------------COMMENT------------

	public CommentDTO findComment(int commId) throws SQLException {
		return commentDAOImpl.findComment(commId);
	}

	public List<CommentDTO> findCommentList(int postId) throws SQLException, PostNotFoundException {
		return commentDAOImpl.findCommentList(postId);
	}

	public int createComment(CommentDTO comm) throws SQLException {
		commentDAOImpl.create(comm);
		int commId = commentDAOImpl.findCommentId();
		System.out.println(commId);
		comm.setCommentId(commId);
		return createAlarm(comm);
	}

	public int update(CommentDTO comm) throws SQLException {
		return commentDAOImpl.update(comm);
	}

	public int delete(int commId) throws SQLException {
		return commentDAOImpl.delete(commId);
	}

	public int updateComment(CommentDTO comment) throws SQLException, CommentNotFoundException {
		return commentDAOImpl.update(comment);
	}

	// -------------SCRAP------------
	public List<ScrapBoxDTO> getScrapList(String userId) throws SQLException {
		return scrapboxDAOImpl.getScrapList(userId);
	}

	public PostDTO findPostInfo(int postId) throws SQLException {
		return postDAOImpl.findPostInfo(postId);
	}

	public int createScrap(String userId, int postId) throws SQLException, ExistingScrapException {
		if (existingScrap(userId, postId) == true) {
			throw new ExistingScrapException("이미 스크랩 된 게시글입니다.");
		}
		ScrapBoxDTO scrap = new ScrapBoxDTO();
		scrap.setUserId(userId);
		scrap.setPostId(postId);
		increaseNumOfScraps(postId);

		return scrapboxDAOImpl.createScrap(scrap);
	}

	public boolean existingScrap(String userId, int postId) {
		return scrapboxDAOImpl.existingScrap(userId, postId);
	}

	public int increaseNumOfScraps(int postId) throws SQLException {
		return scrapboxDAOImpl.increaseNumOfScraps(postId);
	}

	public int getPostIdbyScrap(int scrapNo) throws SQLException {
		return scrapboxDAOImpl.getPostIdbyScrap(scrapNo);
	}

	public int deleteScrap(int scrapNo, int postId) throws SQLException {
		decreaseNumOfScraps(postId);
		return scrapboxDAOImpl.deleteScrap(scrapNo);
	}

	public int deleteScrapBypostId(int postId) throws SQLException {
		return scrapboxDAOImpl.deleteScrapBypostId(postId);
	}

	public int decreaseNumOfScraps(int postId) throws SQLException {
		return scrapboxDAOImpl.decreaseNumOfScraps(postId);
	}

	public List<AlarmDTO> getAlarmList(String userId) throws SQLException {
		return alarmDAOImpl.getAlarmList(userId);
	}

	public AlarmDTO findAlarmInfo(AlarmDTO alarm) throws SQLException {
		PostDTO post = findPostInfo(alarm.getPostId());
		CommentDTO comment = findComment(alarm.getCommentId());

		AlarmDTO resultAlarm = new AlarmDTO(alarm.getAlarmNo(), alarm.getCommentId(), alarm.getPostId(),
				post.getBoardId(), post.getBoardName(), post.getTitle(), comment.getContents());
		return resultAlarm;
	}

	public int createAlarm(CommentDTO comm) throws SQLException {
		AlarmDTO alarm = new AlarmDTO(comm.getCommentId(), comm.getPostId());
		alarm.setUserId(findPostInfo(comm.getPostId()).getUserId());
		return alarmDAOImpl.createAlarm(alarm);
	}

	public int deleteAlarm(int alarmNo) throws SQLException {
		return alarmDAOImpl.deleteAlarm(alarmNo);
	}

}