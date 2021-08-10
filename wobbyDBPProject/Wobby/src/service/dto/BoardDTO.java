package service.dto;

import java.sql.Date;

public class BoardDTO {
	private int boardId;
	private String hobbyId;
	private String clubId;
	private String name;
	private int numOfView;
	private int numOfPost;
	private Date startDate;

	
	
	public BoardDTO() {
		super();
	}
	
	

	public BoardDTO(String clubId) {
		super();
		this.clubId = clubId;
	}


    public BoardDTO(String clubId, String name) {
        super();
        this.clubId = clubId;
        this.name = name;
    }

	public BoardDTO(int boardId, String name, String hobbyId,  String clubId, int numOfView, int numOfPost) {
		super();
		this.boardId = boardId;
		this.name = name;
		this.numOfView = numOfView;
		this.numOfPost = numOfPost;

	}



	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getHobbyId() {
		return hobbyId;
	}
	public void setHobbyId(String hobbyId) {
		this.hobbyId = hobbyId;
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
	public int getNumOfView() {
		return numOfView;
	}
	public void setNumOfView(int numOfView) {
		this.numOfView = numOfView;
	}
	public int getNumOfPost() {
		return numOfPost;
	}
	public void setNumOfPost(int numOfPost) {
		this.numOfPost = numOfPost;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}



	@Override
	public String toString() {
		return "BoardDTO [boardId=" + boardId + ", hobbyId=" + hobbyId + ", clubId=" + clubId + ", name=" + name
				+ ", numOfView=" + numOfView + ", numOfPost=" + numOfPost + ", startDate=" + startDate + "]";
	}
	
	
	
	
}
