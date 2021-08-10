package service.dto;

import java.util.Date;
import java.util.List;

public class ClubDTO {
	private String clubId;
	private String name;
	private String chairId;
	private String chairName;
	private String region;
	private Date startDate;
	/*異붽��븳 寃�  :amountOfPost,popularity,numOfMembers(erwin�뿉�뒗 �뾾�쓬) */
	private int amountOfPost;
	private String popularity;
	private int numOfMembers;
	private int maxNumMembers;
	private String mbti;
	private List<UserDTO> memberList;
	private List<String> category;
	
	


	public ClubDTO(String clubId, String name, String region, int maxNumMembers,String chairId) {
		super();
		this.clubId = clubId;
		this.name = name;
		this.region = region;
		this.maxNumMembers = maxNumMembers;
		this.chairId = chairId;
	}


	public List<String> getCategory() {
		return category;
	}



	public void setCategory(List<String> category) {
		this.category = category;
	}

	public ClubDTO(String clubId, String name, String chairId, int numOfMembers, Date startDate,
			String chairName, String popularity) {
		super();
		this.clubId = clubId;
		this.name = name;
		this.chairId = chairId;
		this.numOfMembers = numOfMembers;
		this.startDate = startDate;
		this.chairName = chairName;
		this.popularity = popularity;
	}

	public ClubDTO(String clubId, String name, String chairId, int numOfMembers, int maxOfmembers, Date startDate,
			String chairName, String region, String popularity) {
		super();
		this.clubId = clubId;
		this.name = name;
		this.chairId = chairId;
		this.numOfMembers = numOfMembers;
		this.maxNumMembers = maxOfmembers;
		this.startDate = startDate;
		this.chairName = chairName;
		this.region = region;
		this.popularity = popularity;
	}
	
	public ClubDTO(String clubId, String name, Date startDate, String chairId, String region) {
		super();
		this.clubId = clubId;
		this.name = name;
		this.chairId = chairId;
		this.startDate = startDate;
		this.region = region;
	}

	
	public ClubDTO(String name,  String region, int maxNumMembers) {
		super();
		this.name = name;
		this.region = region;
		this.maxNumMembers = maxNumMembers;
	}

	
	public String getClubId() {
		return clubId;
	}

	public void setClubId(String clubId) {
		this.clubId = clubId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getChairId() {
		return chairId;
	}

	public void setChairId(String chairId) {
		this.chairId = chairId;
	}
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getNumOfMembers() {
		return numOfMembers;
	}

	public void setNumOfMembers(int numOfMembers) {
		this.numOfMembers = numOfMembers;
	}

	public int getMaxNumMembers() {
		return maxNumMembers;
	}

	public void setMaxNumMembers(int maxNumMembers) {
		this.maxNumMembers = maxNumMembers;
	}

	public List<UserDTO> getMemberList() {
		return memberList;
	}

	public void setMemberList(List<UserDTO> memberList) {
		this.memberList = memberList;
	}

	
	public int getAmountOfPost() {
		return amountOfPost;
	}

	public void setAmountOfPost(int amountOfPost) {
		this.amountOfPost = amountOfPost;
	}

	public String getPopularity() {
		return popularity;
	}

	public void setPopularity(String popularity) {
		this.popularity = popularity;
	}

	
	public String getMbti() {
		return mbti;
	}

	public void setMbti(String mbti) {
		this.mbti = mbti;
	}
	
	public String getChairName() {
		return chairName;
	}



	public void setChairName(String chairName) {
		this.chairName = chairName;
	}



	@Override
	public String toString() {
		return "Club [clubId=" + clubId + ", name=" + name +  ", chairId=" + chairId
				+ ", numOfMembers=" + numOfMembers + ", maxNumMembers=" + maxNumMembers + ", startDate=" + startDate
				+ ", amountOfPost=" + amountOfPost + ", popularity=" + popularity + ", memberList=" + memberList + "]";
	}


	
}
