package persistence.dao.impl;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistence.dao.ConnectionManager;
import persistence.dao.MsgDAO;
import service.dto.MsgDTO;

//������ �߰�
public class MsgDAOImpl implements MsgDAO {

	private static ConnectionManager connMan = new ConnectionManager();
	private JDBCUtil jdbcUtil = null;
	ResultSet rs = null;
	Connection conn = null;
	PreparedStatement pStmt = null;

	public MsgDAOImpl() {
		jdbcUtil = new JDBCUtil();
	}

	/**
	 * ���� Message ��� ��ȯ
	 */
	public List<MsgDTO> ReceiveMsgList(String userId) {
		System.out.println("msgDAOImpl ReceiveMsgList");
		
		String query = "SELECT MESSAGE_NO, SEND_ID, RECIEVE_ID, CONTENTS, SEND_DATE "
				+ "FROM MESSAGE "
				+ "WHERE RECIEVE_ID = ?";
		jdbcUtil.setSqlAndParameters(query, new Object[] {userId});		// JDBCUtil �� query ����
		
		try { 
			ResultSet rs = jdbcUtil.executeQuery();		// query �� ����			
			List<MsgDTO> list = new ArrayList<MsgDTO>();		// MsgDTO ��ü���� ������� list ��ü
			while (rs.next()) {	
				MsgDTO dto = new MsgDTO();		// �ϳ��� MsgDTO ��ü ���� �� ���� ����
				dto.setMsgNo(rs.getString("MESSAGE_NO"));
				dto.setSendId(rs.getString("SEND_ID"));
				dto.setRcverId(rs.getString("RECIEVE_ID"));
				dto.setContent(rs.getString("CONTENTS"));
				dto.setSendDate(rs.getString("SEND_DATE"));
				list.add(dto);		
			}
			return list;		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
		}		
		return null;	
	}
	
	/**
	 * ���� Message ��� ��ȯ
	 */
	public List<MsgDTO> SendMsgList(String userId) {
		System.out.println("msgDAOImpl SendMsgList");
		
		String query = "SELECT MESSAGE_NO, SEND_ID, RECIEVE_ID, CONTENTS, SEND_DATE "
				+ "FROM MESSAGE "
				+ "WHERE SEND_ID = ?";
		jdbcUtil.setSqlAndParameters(query, new Object[] {userId});		// JDBCUtil �� query ����
		
		try { 
			ResultSet rs = jdbcUtil.executeQuery();		// query �� ����			
			List<MsgDTO> list = new ArrayList<MsgDTO>();		// MsgDTO ��ü���� ������� list ��ü
			while (rs.next()) {	
				MsgDTO dto = new MsgDTO();		
				dto.setMsgNo(rs.getString("MESSAGE_NO"));
				dto.setSendId(rs.getString("SEND_ID"));
				dto.setRcverId(rs.getString("RECIEVE_ID"));
				dto.setContent(rs.getString("CONTENTS"));
				dto.setSendDate(rs.getString("SEND_DATE"));
				list.add(dto);		
			}
			return list;		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
		}		
		return null;	
	}
	
	/**
	 * Message 전송 왜 안되냐
	 */
	public int sendMsg(MsgDTO msg) {
		
		System.out.println("msgDAOImpl SendMsg");
		
		String sql = "INSERT INTO STUDENT (MESSAGE_NO, SEND_ID, RECIEVE_ID, SEND_DATE, USER_ID, CONTENTS, TITLE) "
				+ "VALUES (MSG_SEQ.NEXTVAL, ?, ?, SYSDATE, ?, ?, ?) ";
		try {
			
			conn = connMan.getConnection();
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, msg.getSendId());
			pStmt.setString(2, msg.getRcverId());
			pStmt.setString(3, msg.getUserId());
			pStmt.setString(4, msg.getContent());
			pStmt.setString(5, msg.getTitle());
			
			pStmt.addBatch();
			pStmt.executeBatch();
			pStmt.clearParameters();
			pStmt.close();

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
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return 0;
	}

	/**
	 * Message 삭제
	 */
	public int deleteMsg(int msgNo){
		System.out.println("msgDAOImpl deleteMsg");
		
		String sql = "DELETE FROM MESSAGE WHERE MESSAGE_NO=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {msgNo});	// JDBCUtil�� delete���� �Ű� ���� ����

		try {				
			int result = jdbcUtil.executeUpdate();	// delete �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}
	
	/**
	 * Message�� ���� User�� ID, ���� �˻�
	 */
	public int searchMsg(String data) {
		System.out.println("msgDAOImpl searchMsg");
		
		String query = "SELECT MESSAGE_NO, SEND_ID, RECIEVE_ID, CONTENTS, SEND_DATE, TITLE "
				+ "FROM MESSAGE ";
		String sql = query + "WHERE SEND_ID=? OR TEXT=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {data});
	
		try {				
			int result = jdbcUtil.executeUpdate();	// delete �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}

	@Override
	public int deleteMsg(String msgNo) {
		// TODO Auto-generated method stub
		return 0;
	}
}
