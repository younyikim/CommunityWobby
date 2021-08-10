package service.dto;


public class MsgDTO {
	private String msgNo;
	private String rcverId;
	private String sendDate;
	private String sendId;
	private String userId;
	private String content;
	private String title;
	
	public MsgDTO(String rcverId, String title, String content) {
		// TODO Auto-generated constructor stub
		this.rcverId = rcverId;
		this.title = title;
		this.content = content;
		
	}
	public MsgDTO() {
		// TODO Auto-generated constructor stub
	}
	public String getMsgNo() {
		return msgNo;
	}
	public void setMsgNo(String msgNo) {
		this.msgNo = msgNo;
	}
	public String getRcverId() {
		return rcverId;
	}
	public void setRcverId(String rcverId) {
		this.rcverId = rcverId;
	}
	
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	public String getSendId() {
		return sendId;
	}
	public void setSendId(String sendId) {
		this.sendId = sendId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
