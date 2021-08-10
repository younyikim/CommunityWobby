package persistence.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistence.dao.CommentDAO;
import persistence.dao.ConnectionManager;
import service.dto.BoardDTO;
import service.dto.CommentDTO;
import service.dto.PostDTO;

public class CommentDAOImpl implements CommentDAO{
private JDBCUtil jdbcUtil = null;
private static ConnectionManager connMan = new ConnectionManager();

	public CommentDAOImpl() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 媛앹껜 �깮�꽦
	}
	

	public int create(CommentDTO comm){
		Connection conn = null;
	    PreparedStatement pStmt = null;         
	    
		String sql = "INSERT INTO COMMENTS (COMMENTS_ID, POST_ID, USER_ID, CONTENTS,POSTDATE) VALUES (COMM_SEQ.nextval,?,?,?,SYSDATE) ";
		
		String sql2 = "UPDATE POST SET NUMOFCOMMENTS = (SELECT COUNT(COMMENTS_ID) FROM COMMENTS  c WHERE c.POST_ID=? )"
				+ "WHERE POST_ID=? ";
		
		 String sql3 = "UPDATE COMMENTS SET NICKNAME = (SELECT NICKNAME FROM USERINFO"
		      		+ " WHERE USER_ID = ? ) " 
		    		  + "WHERE USER_ID=?";
							
			try {    
				conn = connMan.getConnection();
				pStmt = conn.prepareStatement(sql);
				pStmt.setInt(1, comm.getPostId());
				pStmt.setString(2, comm.getUserId());
				pStmt.setString(3, comm.getContents());
				
				pStmt.addBatch();
				pStmt.executeBatch();
				pStmt.clearParameters();
				pStmt.close();
				
				
				pStmt = conn.prepareStatement(sql2);
				pStmt.setInt(1, comm.getPostId());
				pStmt.setInt(2, comm.getPostId());
				
				pStmt.addBatch();
				pStmt.executeBatch();
				pStmt.clearParameters();
				pStmt.close();
				
				pStmt = conn.prepareStatement(sql3);
				pStmt.setString(1, comm.getUserId());
				pStmt.setString(2, comm.getUserId());
				
				pStmt.addBatch();
				pStmt.executeBatch();
				
				return 1;
			} catch (Exception ex) {
				jdbcUtil.rollback();
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
	
	public int findCommentId(){
        String sql = "SELECT MAX(COMMENTS_ID) AS COMMENTS_ID FROM COMMENTS ";          
        jdbcUtil.setSqlAndParameters(sql, null);   // JDBCUtil�뿉 query臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
         try {
             ResultSet rs = jdbcUtil.executeQuery();  
             if (rs.next()) {
                 return rs.getInt("COMMENTS_ID");     
             }   
          } catch (Exception ex ) {
             ex.printStackTrace();
          }
          finally {   
             if (jdbcUtil != null) {
                jdbcUtil.close();
             }
          }
          return 0;
    }

	
    public int update(CommentDTO comm) {
        String sql = "UPDATE COMMENTS "
                + "SET CONTENTS=?,  UPDATEDATE=SYSDATE "
                + "WHERE COMMENTS_ID=?" ;
        int commId = comm.getCommentId();
    
//      if (clubId.equals("")) clubId = null;
        Object[] param = new Object[] {comm.getContents(), commId};             
        jdbcUtil.setSqlAndParameters(sql, param);   // JDBCUtil�뿉 update臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
            
        try {               
            int result = jdbcUtil.executeUpdate();  // update 臾� �떎�뻾
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        }
        finally {
            jdbcUtil.commit();
            jdbcUtil.close();   // resource 諛섑솚
        }       
        return 0;
    }
    

	
	public int delete(int commId)  {
		String sql = "DELETE FROM COMMENTS WHERE COMMENTS_ID=? ";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {commId});	// JDBCUtil�뿉 delete臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙

		try {				
			int result = jdbcUtil.executeUpdate();	// delete 臾� �떎�뻾
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 諛섑솚
		}		
		return 0;
	}
	
	/**
	 * 二쇱뼱吏�  ID�뿉 �빐�떦�븯�뒗 �뙎湲� �젙蹂대�� �뜲�씠�꽣踰좎씠�뒪�뿉�꽌 李얠븘 COMMENT �룄硫붿씤 �겢�옒�뒪�뿉 
	 * ���옣�븯�뿬 諛섑솚.
	 */
    public CommentDTO findComment(int commId)  {
        String sql = "SELECT COMMENTS_ID, POST_ID, u.USER_ID AS USER_ID, POSTDATE, UPDATEDATE, CONTENTS "
                + "FROM COMMENTS c LEFT OUTER JOIN USERINFO u ON c.USER_ID = u.USER_ID   " 
                + "WHERE COMMENTS_ID = ? ";              
        jdbcUtil.setSqlAndParameters(sql, new Object[] {commId});   // JDBCUtil�뿉 query臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
        CommentDTO comment = null;
        try {
            ResultSet rs = jdbcUtil.executeQuery();     // query �떎�뻾
            if (rs.next()) {                        // �븰�깮 �젙蹂� 諛쒓껄
                comment = new CommentDTO();

                comment.setCommentId(rs.getInt("COMMENTS_ID"));
                comment.setPostId(rs.getInt("POST_ID"));
                comment.setUserId(rs.getString("USER_ID"));
                comment.setPostDate(rs.getDate("POSTDATE"));
                comment.setUpdateDate(rs.getDate("UPDATEDATE"));
                comment.setContents(rs.getString("CONTENTS"));
             
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();       // resource 諛섑솚
        }
        return comment;
    }
	
	public List<CommentDTO> findCommentList(int postId) {
		String sql = "SELECT COMMENTS_ID, POST_ID, USER_ID, CONTENTS, POSTDATE, NICKNAME "
    			+ "FROM COMMENTS "
		   		+ "WHERE POST_ID=? ";        
		jdbcUtil.setSqlAndParameters(sql, new Object[] {postId});  
		 try {
             ResultSet rs = jdbcUtil.executeQuery();               
             List<CommentDTO> commentList = new ArrayList<CommentDTO>();   
             CommentDTO comment = null;
             while (rs.next()) {
            	 comment = new CommentDTO();         

            	 comment.setCommentId(rs.getInt("COMMENTS_ID"));
            	 comment.setPostId(rs.getInt("POST_ID"));
            	 comment.setUserId(rs.getString("USER_ID"));
            	 comment.setContents(rs.getString("CONTENTS"));
            	 comment.setPostDate(rs.getDate("POSTDATE"));
            	 comment.setNickname(rs.getString("NICKNAME"));
             
            	 commentList.add(comment);            
             }   
             jdbcUtil.commit();
             return commentList;               
             
          } catch (Exception ex ) {
             if (ex instanceof AppException) {
                jdbcUtil.rollback();
                System.out.println("rollback");	
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
}
