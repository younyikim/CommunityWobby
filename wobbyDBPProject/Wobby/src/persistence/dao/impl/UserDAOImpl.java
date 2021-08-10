package persistence.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistence.dao.ConnectionManager;
import persistence.dao.UserDAO;
import service.dto.UserDTO;

public class UserDAOImpl implements UserDAO {
	private static ConnectionManager connMan = new ConnectionManager();
	private JDBCUtil jdbcUtil = null;
	ResultSet rs = null;
	Connection conn = null;
	PreparedStatement pStmt = null;

	public UserDAOImpl() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil ��ü ����
	}

	public int create(UserDTO user, String[] hobby) {
		String sql1 = "INSERT INTO USERINFO (USER_ID, PW, NAME, EMAIL, PHONE, REGISTERDATE, REGION, MBTI, NICKNAME)"
				+ "VALUES (?, ?, ?, ?, ?, SYSDATE, ?, ?, ?)";

		String sql2 = "INSERT INTO USERS_HOBBY VALUES (?, ?)";

		try {
			// sql1
			conn = connMan.getConnection();
			pStmt = conn.prepareStatement(sql1);
			pStmt.setString(1, user.getUserId());
			pStmt.setString(2, user.getPassword());
			pStmt.setString(3, user.getName());
			pStmt.setString(4, user.getEmail());
			pStmt.setString(5, user.getPhone());
			pStmt.setString(6, user.getRegion());
			pStmt.setString(7, user.getMbti());
			pStmt.setString(8, user.getNickname());
			// pStmt.setString(6, user.getRegistDate());
			// pStmt.setInt(6, user.getAge());
			// pStmt.setString(7, user.getJob());
			pStmt.addBatch();
			pStmt.executeBatch();
			pStmt.clearParameters();
			pStmt.close();

			// sql2
			for (int i = 0; i < 3; i++) {
				pStmt = conn.prepareStatement(sql2);
				pStmt.setString(1, hobby[i]);
				pStmt.setString(2, user.getUserId());

				pStmt.addBatch();
				pStmt.executeBatch();
				if (i < 2) {
					pStmt.clearParameters();
					pStmt.close();
				}
			}
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

	public int update(UserDTO user, String hobby[]) {
		String sql1 = "UPDATE USERINFO "
				+ "SET pw = ?, name = ?, email = ?, phone = ?, region = ?, mbti = ?, nickname = ? "
				+ "WHERE user_id = ?";

		String sql2 = "DELETE FROM USERS_HOBBY WHERE USER_ID = ?";

		String sql3 = "INSERT INTO USERS_HOBBY VALUES (?, ?)";
		try {
			// sql1
			conn = connMan.getConnection();
			pStmt = conn.prepareStatement(sql1);
			pStmt.setString(1, user.getPassword());
			pStmt.setString(2, user.getName());
			pStmt.setString(3, user.getEmail());
			pStmt.setString(4, user.getPhone());
			pStmt.setString(6, user.getRegion());
			pStmt.setString(8, user.getMbti());
			pStmt.setString(9, user.getNickname());
			pStmt.setString(10, user.getUserId());

			pStmt.addBatch();
			pStmt.executeBatch();
			pStmt.clearParameters();
			pStmt.close();

			// sql2
			pStmt = conn.prepareStatement(sql2);
			pStmt.setString(1, user.getUserId());

			pStmt.addBatch();
			pStmt.executeBatch();
			pStmt.clearParameters();
			pStmt.close();

			// sql3
			for (int i = 0; i < 3; i++) {
				pStmt = conn.prepareStatement(sql3);
				pStmt.setString(1, hobby[i]);
				pStmt.setString(2, user.getUserId());

				pStmt.addBatch();
				pStmt.executeBatch();
				if (i < 2) {
					pStmt.clearParameters();
					pStmt.close();
				}
			}
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

	public int remove(String userId) {
		String sql1 = "DELETE FROM USERS_HOBBY WHERE USER_ID = ?";

		String sql2 = "DELETE FROM USER_CLUB WHERE USER_ID = ?";

		String sql3 = "DELETE FROM POST WHERE USER_ID = ?";
		
		String sql4 = "DELETE FROM ALARMLIST WHERE USER_ID = ?";
		
		String sql5 = "DELETE FROM MESSAGE WHERE RECIEVE_ID = ?";
		
		String sql6 = "DELETE FROM MESSAGE WHERE SEND_ID = ?";

		String sql7 = "DELETE FROM SCRAP WHERE USER_ID = ?";

		String sql8 = "DELETE FROM COMMENTS WHERE USER_ID = ?";

		String sql9 = "DELETE FROM USERINFO WHERE USER_ID = ?";

		try {
			// sql1
			conn = connMan.getConnection();
			System.out.println("시작");

			pStmt = conn.prepareStatement(sql1);
			pStmt.setString(1, userId);

			pStmt.addBatch();
			pStmt.executeBatch();
			pStmt.clearParameters();
			pStmt.close();
			System.out.println("1 끝");
			// sql2
			pStmt = conn.prepareStatement(sql2);
			pStmt.setString(1, userId);

			pStmt.addBatch();
			pStmt.executeBatch();
			pStmt.clearParameters();
			pStmt.close();
			System.out.println("2 끝");

			pStmt = conn.prepareStatement(sql3);
			pStmt.setString(1, userId);
			pStmt.addBatch();
			pStmt.executeBatch();
			pStmt.clearParameters();
			pStmt.close();
			System.out.println("3 끝");

			pStmt = conn.prepareStatement(sql4);
			pStmt.setString(1, userId);
			pStmt.addBatch();
			pStmt.executeBatch();
			pStmt.clearParameters();
			pStmt.close();
			System.out.println("4 끝");

			pStmt = conn.prepareStatement(sql5);
			pStmt.setString(1, userId);
			pStmt.addBatch();
			pStmt.executeBatch();
			pStmt.clearParameters();
			pStmt.close();
			System.out.println("5 끝");

			pStmt = conn.prepareStatement(sql6);
			pStmt.setString(1, userId);
			pStmt.addBatch();
			pStmt.executeBatch();
			pStmt.clearParameters();
			pStmt.close();
			System.out.println("6 끝");
			
			pStmt = conn.prepareStatement(sql7);
			pStmt.setString(1, userId);
			pStmt.addBatch();
			pStmt.executeBatch();
			pStmt.clearParameters();
			pStmt.close();
			System.out.println("7 끝");
			
			pStmt = conn.prepareStatement(sql8);
			pStmt.setString(1, userId);
			pStmt.addBatch();
			pStmt.executeBatch();
			pStmt.clearParameters();
			pStmt.close();
			System.out.println("8 끝");

			pStmt = conn.prepareStatement(sql9);
			pStmt.setString(1, userId);
			pStmt.addBatch();
			pStmt.executeBatch();

			System.out.println("9 끝");

			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
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

		return 0;
	}

	/*
	 * String sql =
	 * "SELECT USER_ID, PW, NAME, EMAIL, PHONE, REGISTERDATE, AGE, u.REGION AS USER_REGION, "
	 * + "JOB, u.MBTI AS USER_MBTI, u.CLUB_ID AS USER_CLUBID, NICKNAME " +
	 * "FROM USERINFO u LEFT OUTER JOIN CLUB c ON u.CLUB_ID = c.CLUB_ID " +
	 * "WHERE USER_ID = ? ";
	 * 
	 */
	@Override
	public UserDTO findUser(String userId) {
		System.out.println("userDAOImpl finduser");
		String sql = "SELECT USER_ID, PW, NAME, EMAIL, PHONE, REGISTERDATE, REGION, " + "MBTI, NICKNAME "
				+ "FROM USERINFO " + "WHERE USER_ID = ? ";

		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId }); // JDBCUtil query Ű
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query
			UserDTO user = null;
			while (rs.next()) { // л ߰
				user = new UserDTO(); // User ü Ͽ л
				user.setUserId(rs.getString("USER_ID"));
				user.setPassword(rs.getString("PW"));
				user.setName(rs.getString("NAME"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPhone(rs.getString("PHONE"));
				user.setRegistDate(rs.getString("REGISTERDATE"));
				user.setRegion(rs.getString("REGION"));
				user.setMbti(rs.getString("MBTI"));
				user.setNickname(rs.getString("NICKNAME"));
			}
			return user;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		jdbcUtil.close();

		return null;
	}

	public List<UserDTO> findUserList() {
		String sql = "SELECT USER_ID, PW, NAME, EMAIL, PHONE, REGISTERDATE, u.REGION AS USER_REGION, "
				+ " u.MBTI AS USER_MBTI, u.CLUB_ID AS USER_CLUBID, NICKNAME "
				+ "FROM USERINFO u LEFT OUTER JOIN CLUB c ON u.CLUB_ID = c.CLUB_ID " + "ORDER BY user_Id";
		jdbcUtil.setSqlAndParameters(sql, null);

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			List<UserDTO> userList = new ArrayList<UserDTO>(); // User���� ����Ʈ ����
			while (rs.next()) {
				UserDTO user = new UserDTO();// User ��ü�� �����Ͽ� ���� ���� ������ ����
				user.setUserId(rs.getString("USER_ID"));
				user.setPassword(rs.getString("PW"));
				user.setName(rs.getString("NAME"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPhone(rs.getString("PHONE"));
				user.setRegistDate(rs.getString("REGISTERDATE"));
				user.setRegion(rs.getString("USER_REGION"));
				user.setMbti(rs.getString("USER_MBTI"));
				user.setClub(rs.getString("USER_CLUBID"));
				user.setNickname(rs.getString("NICKNAME"));
				userList.add(user); // List�� User ��ü ����
			}
			return userList;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		jdbcUtil.close(); // resource ��ȯ

		return null;
	}

	public List<String> findUsersHobby(String userId) {
		String sql = "SELECT HB_ID " + "FROM USERS_HOBBY  " + "WHERE USER_ID = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<String> usersHobby = new ArrayList<String>();
			while (rs.next()) {
				System.out.println(rs.getString("HB_ID"));
				usersHobby.add(rs.getString("HB_ID"));
			}
			return usersHobby;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		jdbcUtil.close();

		return null;
	}

	@Override
	public String findId(String email) {
		System.out.println("userDAOImpl findId");
		String sql = "SELECT USER_ID " + "FROM USERINFO " + "WHERE EMAIL = ? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { email });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			String userId = null;
			if (rs.next()) { // 占싻삼옙 占쏙옙占쏙옙 占쌩곤옙
				userId = rs.getString("USER_ID");
			}
			System.out.println("userId by findId in userDAOimpl : " + userId);
			return userId;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		jdbcUtil.close(); // resource 占쏙옙환

		return null;
	}

	public List<UserDTO> findUsersInClub(int clubId) {
		String sql = "SELECT USER_ID, NAME FROM USERINFO " + "WHERE club_id = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { clubId }); // JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			List<UserDTO> memList = new ArrayList<UserDTO>(); // member���� ����Ʈ ����
			while (rs.next()) {
				UserDTO member = new UserDTO();// User ��ü�� �����Ͽ� ���� ���� ������ ����
				member.setUserId(rs.getString("user_Id"));
				member.setName(rs.getString("name"));
				memList.add(member); // List�� ��ü ����
			}
			return memList;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		jdbcUtil.close(); // resource ��ȯ

		return null;
	}

	public boolean existingUser(String userId) {
		String sql = "SELECT count(*) FROM USERINFO WHERE USER_ID=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId }); // JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		jdbcUtil.close(); // resource ��ȯ

		return false;
	}

	public int updatePw(String userId, String password) {
		String sql = "UPDATE USERINFO " + "SET pw = ? " + "WHERE user_id = ?";
		Object[] param = new Object[] { password, userId };
		jdbcUtil.setSqlAndParameters(sql, param);

		try {
			int result = jdbcUtil.executeUpdate(); // update 占쏙옙 占쏙옙占쏙옙
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		jdbcUtil.commit();
		jdbcUtil.close(); // resource 占쏙옙환

		return 0;
	}

}
