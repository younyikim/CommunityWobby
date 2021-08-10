package persistence.dao;

import java.sql.SQLException;
import java.util.List;
import service.dto.UserDTO;

public interface UserDAO {
	public int create(UserDTO user, String[] hobby);			// �������� �߰�
	public int update(UserDTO user, String[] hobby);		// �������� ����
	public int remove(String userId);			// �������� ����
	public UserDTO findUser(String userId) throws SQLException;   	//���� ���� �˻�
	public List<UserDTO> findUserList();				// ��ü ���� ������ ȹ�� 
	public List<UserDTO> findUsersInClub(int clubId);//Ư�� Ŭ���� ���� ���� ���� ȹ��
	public boolean existingUser(String userId); 	//�����ϴ� ���� ���� Ȯ��
	public String findId(String email);
	public List<String> findUsersHobby(String userId);
}
