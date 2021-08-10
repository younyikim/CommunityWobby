package persistence.dao;

import java.util.List;
import service.dto.ClubDTO;
import service.dto.UserDTO;

public interface ClubDAO {
	public int createClub(ClubDTO club, String[] hobby);

	public int insertUser(String userId, String clubId);

	public int removeUser(String userId, String clubId);

	public int update(ClubDTO club);

	public int updateChair(ClubDTO club);

	public int delete(String clubId);

	public int setClubPopularity(String clubId);
	 
	public ClubDTO findClub(String clubId);

	public List<ClubDTO> findClubList();

	public boolean existingClub(String clubId);

	public ClubDTO regionMatching(UserDTO user);

	public List<String> searchClubList(String keyword);

	public List<String> findClubListbyMBTI(String mbti);

	public List<String> findClubListbyRegion(String region);

	public List<String> findUsersClubIDList(String userId);

	public List<String> findChairsClubIDList(String chairId);

	public List<String> findClubListbyCategory(String first, String second, String third);

	public int increaseClubMemberCnt(String clubId);

	public int decreaseClubMemberCnt(String clubId);

	public int updateClubMbti(String clubId, String mbti);

	public String findMaxOfMbtiInClub(String clubId);
	
	 public String getChairId(String clubID); 
}
