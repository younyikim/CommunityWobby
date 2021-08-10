package persistence.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistence.dao.impl.JDBCUtil;
import persistence.dao.ConnectionManager;
import persistence.dao.PostDAO;
import service.dto.BoardDTO;
import service.dto.PostDTO;

public class PostDAOImpl implements PostDAO {
private JDBCUtil jdbcUtil = null;
private static ConnectionManager connMan = new ConnectionManager();
   
   public PostDAOImpl() {         
      jdbcUtil = new JDBCUtil();   // JDBCUtil 
   }
   
   public int create(PostDTO post, int boardId) {
      Connection conn = null;
      PreparedStatement pStmt = null;         
      ResultSet rs = null;
      
      String sql = "INSERT INTO Post (POST_ID, POSTDATE, USER_ID, TITLE, CONTENTS,BOARD_ID) VALUES (POST_SEQ.nextval, SYSDATE, ?, ?, ?, ?)";
      
      String sql2 = "UPDATE BOARD SET NUMOFPOST = (SELECT COUNT(POST_ID) FROM POST p  WHERE p.BOARD_ID=? ) " + 
            "      WHERE BOARD_ID=?";
      
      String sql3 = "UPDATE POST SET NICKNAME = (SELECT NICKNAME FROM USERINFO"
      		+ " WHERE USER_ID = ? ) " 
    		  + "WHERE USER_ID=?";
         try {   
            //sql1
            conn = connMan.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, post.getUserId());
            pStmt.setString(2, post.getTitle());
            pStmt.setString(3, post.getContents());
            pStmt.setInt(4, boardId);
             
            pStmt.addBatch();
            pStmt.executeBatch();
            pStmt.clearParameters();
            pStmt.close();
            
            //sql2
            pStmt = conn.prepareStatement(sql2);
            pStmt.setInt(1, boardId);
            pStmt.setInt(2, boardId);
            
            pStmt.addBatch();
            pStmt.executeBatch();
            pStmt.clearParameters();
            pStmt.close();
            
          //sql3
            pStmt = conn.prepareStatement(sql3);
            pStmt.setString(1, post.getUserId());
            pStmt.setString(2, post.getUserId());
            
            pStmt.addBatch();
            pStmt.executeBatch();
            
   
            return 1;
         } catch (Exception ex) {
            ex.printStackTrace();
         } finally {      
            if (pStmt != null) {
               try {
                  pStmt.close();
               } catch (SQLException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
               }
            }
            if (conn != null) 
               try {
                  conn.rollback();
                  conn.close();
               }catch(SQLException ex) {
                  ex.printStackTrace();
               }
            
         }      
         return 0;
   }
   
   /**
    * post瑜� �닔�젙 
    */
   public int update(PostDTO post) {
      String sql = "UPDATE Post "
            + "SET TITLE=?, CONTENTS = ?, UPDATEDATE=SYSDATE "
            + "WHERE POST_ID=? ";
      int postId = post.getPostId();
   
      Object[] param = new Object[] {post.getTitle(), post.getContents(), post.getPostId()};            
      jdbcUtil.setSqlAndParameters(sql, param);   
         
      try {            
         int result = jdbcUtil.executeUpdate();   
         return result;
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
      }
      finally {
         jdbcUtil.commit();
         jdbcUtil.close();  
      }      
      return 0;
      
   }
   
   /**
    * post �궘�젣 
    */
   public int delete(int postId)  {
      String sql = "DELETE FROM POST WHERE POST_ID=?";      
      jdbcUtil.setSqlAndParameters(sql, new Object[] {postId});   

      try {            
         int result = jdbcUtil.executeUpdate();   
         return result;
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
      }
      
         jdbcUtil.commit();
         jdbcUtil.close();   
            
      return 0;
   }
   
   /*
    * post 議고쉶 �닔 
    * */
   public int increasePostViewCnt(int postId) {
      String sql = "UPDATE Post "
            + "SET NUMOFVIEW=NVL(NUMOFVIEW,0) + 1 "
            + "WHERE POST_ID=?";
            
      jdbcUtil.setSqlAndParameters(sql, new Object[] {postId});   
         
      try {            
         int result = jdbcUtil.executeUpdate();   
         return result;
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
      }
      finally {
         jdbcUtil.commit();
         jdbcUtil.close();   
      }      
      return 0;
   }
   
   /*
    * post �뒪�겕�옪�닔
    * */
   public int increasePostScrapCnt(int postId) {
      String sql = "UPDATE Post "
            + "SET NUMOFSCRAPS=NVL(NUMOFSCRAPS,0) + 1 "
            + "WHERE POST_ID=?";
            
      jdbcUtil.setSqlAndParameters(sql, new Object[] {postId});   
         
      try {            
         int result = jdbcUtil.executeUpdate();   
         return result;
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
      }
      finally {
         jdbcUtil.commit();
         jdbcUtil.close();   
      }      
      return 0;
   }
   
   /**
    * �씤湲� post瑜� list濡� 諛섑솚 
    */
    public List<PostDTO> findPopularPostList(int boardId) {
       String sql = "SELECT POST_ID, TITLE, NICKNAME, POSTDATE ,NUMOFVIEW, NUMOFSCRAPS "
                + "FROM POST " 
               + "WHERE BOARD_ID=? AND NUMOFVIEW >= 10 AND NUMOFSCRAPS >= 10 "
                  + "ORDER BY NUMOFVIEW ";        
               jdbcUtil.setSqlAndParameters(sql, new Object[] {boardId});      
                        
               try {
                  ResultSet rs = jdbcUtil.executeQuery();              
                  List<PostDTO> postList = new ArrayList<PostDTO>();   
                  PostDTO post = null;
                  while (rs.next()) {
                     post = new PostDTO();         

                     post.setPostId(rs.getInt("POST_ID"));
                     post.setTitle(rs.getString("TITLE"));
                     post.setNickname(rs.getString("NICKNAME"));
                     post.setPostDate(rs.getDate("POSTDATE"));
                     post.setNumOfView(rs.getInt("NUMOFVIEW"));
                     post.setNumOfScraps(rs.getInt("NUMOFSCRAPS"));
                     
                     postList.add(post);            
                  }   
                  jdbcUtil.commit();
                  return postList;               
                  
               } catch (Exception ex ) {
                  if (ex instanceof AppException) {
                     jdbcUtil.rollback();
                     System.out.println("�듃�옖�옲�뀡�씠 rollback �릺�뿀�뒿�땲�떎.");	
                  }
                  ex.printStackTrace();
               }
               finally {   
                  if (jdbcUtil != null) {
                     jdbcUtil.close();
                  }
               }
               return null;
    }
   /**
    * post 李얘린 
    */
   public PostDTO findPost(int postId)  {
	   String sql = "SELECT POST_ID, USER_ID, TITLE, NICKNAME, POSTDATE ,NUMOFVIEW, NUMOFSCRAPS, BOARD_ID, CONTENTS "
	             	+ "FROM POST " 
	             	+ "WHERE POST_ID=? ";
	                          
      jdbcUtil.setSqlAndParameters(sql, new Object[] {postId});   
      PostDTO post = null;
      try {
         ResultSet rs = jdbcUtil.executeQuery();     
         if (rs.next()) {                  
            post = new PostDTO();     
            
            post.setPostId(rs.getInt("POST_ID"));
            post.setUserId(rs.getString("USER_ID"));
            post.setTitle(rs.getString("TITLE"));
            post.setNickname(rs.getString("NICKNAME"));
            post.setPostDate(rs.getDate("POSTDATE"));
            post.setNumOfView(rs.getInt("NUMOFVIEW"));
            post.setNumOfScraps(rs.getInt("NUMOFSCRAPS"));
            post.setBoardId(rs.getInt("BOARD_ID"));
            post.setContents(rs.getString("CONTENTS"));
         }
      } catch (Exception ex ) {
          if (ex instanceof AppException) {
              jdbcUtil.rollback();
              System.out.println("�듃�옖�옲�뀡�씠 rollback �릺�뿀�뒿�땲�떎.");	
           }
           ex.printStackTrace();
        }
        finally {   
           if (jdbcUtil != null) {
              jdbcUtil.close();
           }
        }
      return post;
   }
   
   /**
    * boardId�뿉 �빐�떦�븯�뒗 post瑜� list濡� 諛섑솚 
    */
   public List<PostDTO> findPostList(int boardId)  {
      String sql = "SELECT POST_ID, TITLE, NICKNAME, POSTDATE ,NUMOFVIEW, NUMOFSCRAPS, NUMOFCOMMENTS, BOARD_ID "
             + "FROM POST " 
            + "WHERE BOARD_ID=? "
               + "ORDER BY POST_ID ";
      		System.out.println("PostDAO - findPostList Start");
            jdbcUtil.setSqlAndParameters(sql, new Object[] {boardId});      
                     
            try {
               ResultSet rs = jdbcUtil.executeQuery();               
               List<PostDTO> postList = new ArrayList<PostDTO>();   
               PostDTO post = null;
               while (rs.next()) {
                  post = new PostDTO();         

                  post.setPostId(rs.getInt("POST_ID"));
                  post.setTitle(rs.getString("TITLE"));
                  post.setNickname(rs.getString("NICKNAME"));
                  post.setPostDate(rs.getDate("POSTDATE"));
                  post.setNumOfView(rs.getInt("NUMOFVIEW"));
                  post.setNumOfScraps(rs.getInt("NUMOFSCRAPS"));
                  post.setNumOfComments(rs.getInt("NUMOFCOMMENTS"));
                  post.setBoardId(rs.getInt("BOARD_ID"));
                  
                  postList.add(post);            
               }   
               jdbcUtil.commit();
               return postList;               
               
            } catch (Exception ex ) {
               if (ex instanceof AppException) {
                  jdbcUtil.rollback();
                  System.out.println("�듃�옖�옲�뀡�씠 rollback �릺�뿀�뒿�땲�떎.");	
               }
               ex.printStackTrace();
            }
            finally {   
               if (jdbcUtil != null) {
                  jdbcUtil.close();
               }
            }
            return null;
	   
//	   System.out.println("PostDAO - findPostList Start");
//	      String sql = "SELECT POST_ID, TITLE "
//	             + "FROM POST " 
//	            + "WHERE BOARD_ID=? "
//	               + "ORDER BY POST_ID ";        
//	            jdbcUtil.setSqlAndParameters(sql, new Object[] {boardId}); 
//	                     
//	            try {
//	               ResultSet rs = jdbcUtil.executeQuery();   
//	               List<PostDTO> postList = new ArrayList<PostDTO>();   
//	               PostDTO post = null;
//	               while (rs.next()) {
//	System.out.println(rs.getString("TITLE"));
//	                  post = new PostDTO(); 
//	                  post.setPostId(rs.getInt("POST_ID"));
//	                  post.setTitle(rs.getString("TITLE"));
//
//	                  postList.add(post);                     }   
//	               jdbcUtil.commit();
//	               return postList;               
//	               
//	            } catch (Exception ex ) {
//	               if (ex instanceof AppException) {
//	                  jdbcUtil.rollback();
//	                  System.out.println("");   
//	               }
//	               ex.printStackTrace();
//	            }
//	            finally {   
//	               if (jdbcUtil != null) {
//	                  jdbcUtil.close();
//	               }
//	            }
//	            return null;

   }
   
    public List<PostDTO> searchPostList(String keyword)  {
        String sql = "SELECT POST_ID,BOARD_ID, TITLE, NICKNAME, POSTDATE, NUMOFVIEW, NUMOFSCRAPS "
                + "FROM POST "
                + "WHERE CONTENTS LIKE '%'||?||'%' "
                + "ORDER BY POST_ID ";        
                jdbcUtil.setSqlAndParameters(sql, new Object[] {keyword});      
                           
                try {
                    ResultSet rs = jdbcUtil.executeQuery();                  
                    List<PostDTO> postList = new ArrayList<PostDTO>();  
                    PostDTO post = null;
                    while (rs.next()) {
                        post = new PostDTO();           
                        post.setPostId(rs.getInt("POST_ID"));
                        post.setBoardId(rs.getInt("BOARD_ID"));
                        post.setTitle(rs.getString("TITLE"));
                        post.setNickname(rs.getString("NICKNAME"));
                        post.setPostDate(rs.getDate("POSTDATE"));
                        post.setNumOfView(rs.getInt("NUMOFVIEW"));
                        post.setNumOfScraps(rs.getInt("NUMOFSCRAPS"));
                        
                        postList.add(post);           
                    }   
                    return postList;                    
                    
                } catch (Exception ex ) {
                    ex.printStackTrace();
                }
                jdbcUtil.close();
                return null;
    }
    
    public int decreaseNumOfScraps(int postId) {
        String sql = "UPDATE POST SET NUMOFSCRAPS = NUMOFSCRAPS - 1 " + "      WHERE POST_ID=?";   
        jdbcUtil.setSqlAndParameters(sql,  new Object[] {postId});   // JDBCUtil �쐻 �윥 諭� update  �떛怜얇꺂梨뜻쾮  蒻븍슢�뵞 嫄�     �녃域밟뫁�굲 �쐻 �윥 鍮�  �쐻 �윞 留� �쐻 �윪 �젳
           
        try {            
           int result = jdbcUtil.executeUpdate();   // update   �떛 �쐻   �쐻 �윥�젆袁��쐻 �윥�뀷 
           return result;
        } catch (Exception ex) {
           jdbcUtil.rollback();
           ex.printStackTrace();
        }
        finally {
           jdbcUtil.commit();
           jdbcUtil.close();   // resource  �쎗 猷뉒몭   瑗�
        }      
        return 0;
    }
    
   /**
    * post媛� 議댁옱�븯�뒗吏� 寃��궗 
    */
   public boolean existingPost(int postId)  {
      String sql = "SELECT count(*) FROM POST WEHRE POST_ID=?";      
      jdbcUtil.setSqlAndParameters(sql, new Object[] {postId});   

      try {
         ResultSet rs = jdbcUtil.executeQuery();      
         if (rs.next()) {
            int count = rs.getInt(1);
            return (count == 1 ? true : false);
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         jdbcUtil.close();      
      }
      return false;
   }
   
   //2020-12-20
   public PostDTO findPostInfo(int postId)  {
       String sql = "SELECT POST_ID, POSTDATE, USER_ID, NICKNAME, TITLE, p.BOARD_ID AS BOARD_ID, b.NAME AS BOARD_NAME "
              //+ "POSTDATE,  UPDATEDATE, CONTENTS,  "
              + "FROM POST p LEFT OUTER JOIN BOARD b ON p.BOARD_ID = b.BOARD_ID   "
              + "WHERE p.POST_ID = ? ";              
       jdbcUtil.setSqlAndParameters(sql, new Object[] {postId});   // JDBCUtil �쐻 �윥 諭� query  �떛怜얇꺂梨뜻쾮  蒻븍슢�뵞 嫄�     �녃域밟뫁�굲 �쐻 �윥 鍮�  �쐻 �윞 留� �쐻 �윪 �젳
       PostDTO post = null;
       try {
          ResultSet rs = jdbcUtil.executeQuery();
          if (rs.next()) {
              post = new PostDTO();           // Community  琉� 猷뉔뜮�꼯�쇊 裕곭킄�떣�쐻   �쐻 �윞�눧硫⑤쐻 �윞 �렰 �쐻 �윥�뵳  �쐻 �윥 肉�  �쐻 �윪野껉막�쐻 �윪 沅�  �쐻 �윥�뀷�껊쐻 �윪甕�   �쐻 �윪 �젳 �녃域�   �쐻 猷� �굲  �쐻 猷� �굲 �쐻 �윪 沅�
              post.setPostId(rs.getInt("POST_ID"));
              post.setPostDate(rs.getDate("POSTDATE"));
              post.setUserId(rs.getString("USER_ID"));
              post.setNickname(rs.getString("NICKNAME"));
              post.setTitle(rs.getString("TITLE"));
              post.setBoardId(rs.getInt("BOARD_ID"));
              post.setBoardName(rs.getString("BOARD_NAME"));
          }   
          jdbcUtil.commit();
          return post;
       } catch (Exception ex) {
          ex.printStackTrace();
       } finally {
          jdbcUtil.close();      // resource  �쎗 猷뉒몭   瑗�
       }
       return null;
    }
   
   public int increaseNumOfScraps(int postId) {
       String sql = "UPDATE POST SET NUMOFSCRAPS = NUMOFSCRAPS+1" + " WHERE POST_ID=?";   
       jdbcUtil.setSqlAndParameters(sql,  new Object[] {postId});   // JDBCUtil �쐻 �윥 諭� update  �떛怜얇꺂梨뜻쾮  蒻븍슢�뵞 嫄�     �녃域밟뫁�굲 �쐻 �윥 鍮�  �쐻 �윞 留� �쐻 �윪 �젳
          
       try {            
          int result = jdbcUtil.executeUpdate();   // update   �떛 �쐻   �쐻 �윥�젆袁��쐻 �윥�뀷 
          return result;
       } catch (Exception ex) {
          jdbcUtil.rollback();
          ex.printStackTrace();
       }
       finally {
          jdbcUtil.commit();
          jdbcUtil.close();   // resource  �쎗 猷뉒몭   瑗�
       }      
       return 0;
   }
}