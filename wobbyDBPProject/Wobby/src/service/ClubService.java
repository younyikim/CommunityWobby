package service;

import java.util.List;

import service.dto.ClubDTO;
import service.dto.UserDTO;

public interface ClubService {
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

	public List<ClubDTO> searchClubList(String keyword);

	public List<String> findClubListbyMBTI(String mbti);

	public List<String> findClubListbyRegion(String region);

	public List<String> findUsersClubIDList(String userId);
	
	 public String getChairId(String clubID); 
}

