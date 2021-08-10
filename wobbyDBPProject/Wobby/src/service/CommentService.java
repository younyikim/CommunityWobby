package service;

import java.util.List;

import service.dto.CommentDTO;

public interface CommentService {
	public int create(CommentDTO comm);
	public int update(CommentDTO comm);
	public int delete(int commId);
	public CommentDTO findComment(int commId);
	public int findCommentId();
	public List<CommentDTO> findCommentList(int postId);
}
