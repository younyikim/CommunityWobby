package service.dto;

import java.util.Date;

public class CommentDTO {
	private int commentId;
	private int postId;
	private String userId;
	private Date postDate;
	private Date updateDate;
	private String contents;
	private String nickname;
	
	public CommentDTO() {
		super();
	}
	
	public CommentDTO(int postId, String userId, String contents) {
        super();
        this.postId = postId;
        this.userId = userId;
        this.contents = contents;
	}
	
	public CommentDTO(int commentId, int postId, String userId, Date postDate, 
			Date updateDate, String contents, String nickname) {
		super();
		this.commentId = commentId;
		this.postId = postId;
		this.userId = userId;
		this.postDate = postDate;
		this.updateDate = updateDate;
		this.contents = contents;
		this.nickname = nickname;
	}


	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	
	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	@Override
	public String toString() {
		return "CommentDTO [commentId=" + commentId + ", postId=" + postId + ", userId=" + userId + ", postDate="
				+ postDate + "]";
	}
	
	
}
