package service.dto;

public class AlarmDTO {
   private int alarmNo;
   private int commentId;
   private int postId;
   private int boardId;
    private String userId;
   private String boardName;
   private String title;
   private String commentContent;
   
   
   public AlarmDTO() {
        super();
    }
    
    public AlarmDTO(int commentId, int postId) {
        super();
        this.commentId = commentId;
        this.postId = postId;
    }
   
   public AlarmDTO(int alarmNo, int commentId, int postId) {
        super();
        this.alarmNo = alarmNo;
        this.commentId = commentId;
        this.postId = postId;
    }
   
    public AlarmDTO(int alarmNo, int commentId, int postId, int boardId, String boardName, String title,
            String commentContent) {
        super();
        this.alarmNo = alarmNo;
        this.commentId = commentId;
        this.postId = postId;
        this.boardId = boardId;
        this.boardName = boardName;
        this.title = title;
        this.commentContent = commentContent;
    }

    public int getAlarmNo() {
      return alarmNo;
   }
   public void setAlarmNo(int alarmNo) {
      this.alarmNo = alarmNo;
   }
   public int getCommentId() {
      return commentId;
   }
   public void setCommentId(int commentId) {
      this.commentId = commentId;
   }
   public int getPostNo() {
      return postId;
   }
   public void setPostNo(int postId) {
      this.postId = postId;
   }
    public int getPostId() {
        return postId;
    }
    public void setPostId(int postId) {
        this.postId = postId;
    }
    public int getBoardId() {
        return boardId;
    }
    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }
    public String getBoardName() {
        return boardName;
    }
    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getCommentContent() {
        return commentContent;
    }
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AlarmDTO [alarmNo=" + alarmNo + ", commentId=" + commentId + ", postId=" + postId + ", boardId="
                + boardId + ", userId=" + userId + ", boardName=" + boardName + ", title=" + title + ", commentContent="
                + commentContent + "]";
    }
   
   
}