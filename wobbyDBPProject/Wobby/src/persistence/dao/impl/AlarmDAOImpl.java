package persistence.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistence.dao.AlarmDAO;
import persistence.dao.ConnectionManager;
import service.dto.AlarmDTO;
import service.dto.CommentDTO;
import service.dto.MsgDTO;
import service.dto.ScrapBoxDTO;

public class AlarmDAOImpl implements AlarmDAO {

    private JDBCUtil jdbcUtil = null;
    private static ConnectionManager connMan = new ConnectionManager();

	public AlarmDAOImpl() {
		jdbcUtil = new JDBCUtil();
	}

	public List<AlarmDTO> getAlarmList(String userId) {
		String query = "SELECT ALARMLIST_NO, POST_ID, COMMENT_ID " + 
						"FROM ALARMLIST " + 
						"WHERE USER_ID = ?";
		jdbcUtil.setSqlAndParameters(query, new Object[] { userId }); 

		try {
		    ResultSet rs = jdbcUtil.executeQuery(); 
			List<AlarmDTO> list = new ArrayList<AlarmDTO>(); 
			while (rs.next()) {
				AlarmDTO dto = new AlarmDTO();
				dto.setAlarmNo(rs.getInt("ALARMLIST_NO"));
				dto.setPostId(rs.getInt("POST_ID"));
				dto.setCommentId(rs.getInt("COMMENT_ID"));
				list.add(dto);
			}
			return list;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return null;
	}
	
	 public int createAlarm(AlarmDTO alarm) {
	        String sql = "INSERT INTO ALARMLIST VALUES (ALARM_SEQ.NEXTVAL, ?, ?, ?)";

	        Object[] param = new Object[] { alarm.getUserId(), alarm.getPostId(), alarm.getCommentId() };
	        jdbcUtil.setSqlAndParameters(sql, param); 
	        try {
	            int result = jdbcUtil.executeUpdate(); 
	            return result;
	        } catch (Exception ex) {
	            jdbcUtil.rollback();
	            ex.printStackTrace();
	        } finally {
	            jdbcUtil.commit();
	            jdbcUtil.close(); // resource  �쐻 猷� �굲 �넎
	        }
	        return 0;
	   }
	 

	public int deleteAlarm(int alarmNo) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM ALARMLIST WHERE ALARMLIST_NO = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { alarmNo }); 

		try {
			int result = jdbcUtil.executeUpdate(); 
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); 
		}
		return 0;
	}
}
