package service.dto;

import java.sql.Date;

public class ScrapBoxDTO {
   private int scrapNo;
   private int postId;
   private String userId;
   private String nickname;
   private Date scrapDate;
   private Date postDate;
   private String title;
   private int boardId;
   private String boardName;
   
   
   public ScrapBoxDTO() {
      super();
   }
   
   public ScrapBoxDTO(String title, String boardName) {
       super();
       this.title = title;
       this.boardName = boardName;
    }
    
   public ScrapBoxDTO(int scrapNo, int postId, String userId, String nickname, Date postDate, String title, int boardId,
      String boardName) {
        super();
        this.scrapNo = scrapNo;
        this.postId = postId;
        this.userId = userId;
        this.nickname = nickname;
        this.postDate = postDate;
        this.title = title;
        this.boardId = boardId;
        this.boardName = boardName;
    }
       
public String getBoardName() {
        return boardName;
    }
    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }
    public Date getScrapDate() {
        return scrapDate;
    }
    public void setScrapDate(Date scrapDate) {
        this.scrapDate = scrapDate;
    }
    public int getScrapNo() {
      return scrapNo;
   }
   public void setScrapNo(int scrapNo) {
       this.scrapNo = scrapNo;
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
   
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public Date getPostDate() {
        return postDate;
    }
    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getBoardId() {
        return boardId;
    }
    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }
       
   
}