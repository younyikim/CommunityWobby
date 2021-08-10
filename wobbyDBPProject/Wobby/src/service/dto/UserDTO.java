package service.dto;

import java.sql.Array;
import java.util.List;

public class UserDTO {
   private String userId;
   private String password;
   private String name;
   private String email;
   private String phone;
   private String registDate;
   private int age;
   private String region;
   private String job;
   private String mbti;
   private String clubId;
   private String nickname;
   

  
   public UserDTO(String userId, String password, String name, String nickname, String email, Array Hobby, String mbti, String region) {
      // TODO Auto-generated constructor stub
      this.userId = userId;
      this.password = password;
      this.name = name;
      this.nickname = nickname;
      this.email = email;
      //생년월일
      this.mbti = mbti;
      this.region = region;
   }
   
   // 가입signUp에 사용되는 생성자
   public UserDTO(String userId, String password, String name, String email, String phone, String region,
		String mbti, String nickname) {
	super();
	this.userId = userId;
	this.password = password;
	this.name = name;
	this.email = email;
	this.phone = phone;
	this.region = region;
	this.mbti = mbti;
	this.nickname = nickname;
}

public UserDTO() {
      // TODO Auto-generated constructor stub
   }
   public String getUserId() {
      return userId;
   }
   public void setUserId(String userId) {
      this.userId = userId;
   }
   public String getPassword() {
      return password;
   }
   public void setPassword(String password) {
      this.password = password;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getEmail() {
      return email;
   }
   public void setEmail(String email) {
      this.email = email;
   }
   public String getPhone() {
      return phone;
   }
   public void setPhone(String phone) {
      this.phone = phone;
   }
   public String getRegistDate() {
      return registDate;
   }
   public void setRegistDate(String registDate) {
      this.registDate = registDate;
   }
   public int getAge() {
      return age;
   }
   public void setAge(int age) {
      this.age = age;
   }
   public String getRegion() {
      return region;
   }
   public void setRegion(String region) {
      this.region = region;
   }
   public String getJob() {
      return job;
   }
   public void setJob(String job) {
      this.job = job;
   }
   public String getMbti() {
      return mbti;
   }
   public void setMbti(String mbti) {
      this.mbti = mbti;
   }
   
   public String getClub() {
      return clubId;
   }
   public void setClub(String club) {
      this.clubId = club;
   }
   public String getNickname() {
      return nickname;
   }
   public void setNickname(String nickname) {
      this.nickname = nickname;
   }
  
   public boolean matchPassword(String password) {
      if (password == null) {
         return false;
      }
      return this.password.equals(password);
   }
   
   public boolean isSameUser(String userid) {
        return this.userId.equals(userid);
    }
   

   
}