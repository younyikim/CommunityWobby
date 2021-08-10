package persistence.dao;

import java.util.List;

import service.dto.CommentDTO;

public interface CommentDAO {
	public int create(CommentDTO comm);
	public int update(CommentDTO comm);
	public int delete(int commId);
	public int findCommentId();
	public CommentDTO findComment(int commId);
	public List<CommentDTO> findCommentList(int postId);
}
