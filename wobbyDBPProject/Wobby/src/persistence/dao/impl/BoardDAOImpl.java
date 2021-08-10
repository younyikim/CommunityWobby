package persistence.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import persistence.dao.BoardDAO;
import persistence.dao.impl.JDBCUtil;
import service.dto.BoardDTO;
import service.dto.ClubDTO;

import java.sql.Connection;

public class BoardDAOImpl implements BoardDAO {
private JDBCUtil jdbcUtil = null;
static Connection conn = null;

	public BoardDAOImpl() {			
		jdbcUtil = new JDBCUtil();	
	}
	
	public BoardDTO create(BoardDTO board) {
		
		String sql = "INSERT INTO Board (BOARD_ID, HB_ID, CLUB_ID, NAME) VALUES (BOARD_SEQ.nextval,?,?,?) ";
		
			try {
				Object[] param = new Object[] { board.getHobbyId(), board.getClubId(), board.getName()};				
				jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �뿉 insert臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
									
				String key[] = {"BOARD_ID"};	// PK 而щ읆紐�
				
				if(jdbcUtil.executeUpdate(key) != 1) { // insert 臾� �떎�뻾
					throw new AppException();
				}
				
			   	ResultSet rs = jdbcUtil.getGeneratedKeys();
			   	if(rs.next()) {
			   		int generatedKey = rs.getInt(1);   // �깮�꽦�맂 PK 媛�
			   		board.setBoardId(generatedKey); 	// id�븘�뱶�뿉 ���옣  
			   	}
			   	jdbcUtil.commit();
			   	return board;
			   	
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
	

	public int update(BoardDTO board) {
		String sql = "UPDATE Board "
				+ "SET HB_ID=?, NAME=? "
				+ "WHERE BOARD_ID=?" ;
		int boardId = board.getBoardId();
	
//		System.out.println(board.getHobbyId() + " " + board.getName() + " " + board.getClubId() + " "+ boardId);

		Object[] param = new Object[] {board.getHobbyId(), board.getName(), boardId};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil�뿉 update臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
		
		try {	
			int result = jdbcUtil.executeUpdate();	// update 臾� �떎�뻾
//			if (result != 1) {
//				throw new AppException();
//			}
		 	jdbcUtil.commit();
			return result;
			
		}   catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 諛섑솚
		}		
		return 0;
	}
	

	public int delete(int boardId)  {
		String sql = "DELETE FROM Board WHERE BOARD_ID=? ";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {boardId});	// JDBCUtil�뿉 delete臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙

		try {
			int result = jdbcUtil.executeUpdate();	// delete 臾� �떎�뻾
			
			if (result != 1) {
				throw new AppException();
			}
		 	jdbcUtil.commit();
			return result;
		}  catch (Exception ex ) {
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
		return 0;
	}
	
	public int findBoardId(String clubId)  {
		String sql = "SELECT BOARD_ID FROM BOARD WHERE CLUB_ID = ? ";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {clubId});	// JDBCUtil�뿉 delete臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙

		try {
		     ResultSet rs = jdbcUtil.executeQuery();
	            if (rs.next()) {
	                return rs.getInt("BOARD_ID");
	            } 
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
		return 0;
	}

	
	public BoardDTO findBoard(int boardId)  {
		String sql = "SELECT NAME, HB_ID, CLUB_ID,STARTDATE, NUMOFVIEW, NUMOFPOST "
    			+ "FROM Board "
    			+ "WHERE BOARD_ID=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {boardId});	// JDBCUtil�뿉 query臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
		BoardDTO board = new BoardDTO();
		try {
			
			ResultSet rs = jdbcUtil.executeQuery();		// query �떎�뻾
			
			if (rs.next()) {					
				board.setBoardId(boardId);
				board.setName(rs.getString("NAME"));
				board.setHobbyId(rs.getString("HB_ID"));
				board.setClubId(rs.getString("CLUB_ID"));
				board.setStartDate(rs.getDate("STARTDATE"));
				board.setNumOfView(rs.getInt("NUMOFVIEW"));
				board.setNumOfPost(rs.getInt("NUMOFPOST"));
			}
			jdbcUtil.commit();
			return board;
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
	
	
	public List<BoardDTO> findBoardList()  {
		String sql = "SELECT BOARD_ID, NAME, HB_ID, CLUB_ID, STARTDATE,NUMOFVIEW, NUMOFPOST "
    			+ "FROM Board "
		   		+ "ORDER BY name ";        
				jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�뿉 query臾� �꽕�젙
							
				try {
					ResultSet rs = jdbcUtil.executeQuery();			// query �떎�뻾			
					List<BoardDTO> boardList = new ArrayList<BoardDTO>();	// Community�뱾�쓽 由ъ뒪�듃 �깮�꽦
					BoardDTO board = null;
					while (rs.next()) {
						board = new BoardDTO();			// Community 媛앹껜瑜� �깮�꽦�븯�뿬 �쁽�옱 �뻾�쓽 �젙蹂대�� ���옣

						board.setBoardId(rs.getInt("BOARD_ID"));
						board.setName(rs.getString("NAME"));
						board.setHobbyId(rs.getString("HB_ID"));
						board.setClubId(rs.getString("CLUB_ID"));
						board.setStartDate(rs.getDate("STARTDATE"));
						board.setNumOfView(rs.getInt("NUMOFVIEW"));
						board.setNumOfPost(rs.getInt("NUMOFPOST"));
						
						boardList.add(board);				// List�뿉 Community 媛앹껜 ���옣
					}	
					jdbcUtil.commit();
					return boardList;					
					
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
	

	public boolean existingBoard(int boardId) {
		String sql = "SELECT count(*) FROM Board WHERE BOARD_ID=? ";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {boardId});	// JDBCUtil�뿉 query臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query �떎�뻾
			if (rs.next()) {
				int count = rs.getInt(1);
				jdbcUtil.commit();
				return (count == 1 ? true : false);
			}
		
			
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
		return false;
	}
	
}
