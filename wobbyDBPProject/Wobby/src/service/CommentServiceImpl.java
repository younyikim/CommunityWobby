package service;

import java.util.List;

import persistence.DAOFactory;
import persistence.dao.CommentDAO;
import service.dto.CommentDTO;

public class CommentServiceImpl {
private CommentDAO dao = null;
	
	public CommentServiceImpl() {
		DAOFactory factory = new DAOFactory();
		dao = factory.getCommentDAO();
	}
	public int create(CommentDTO comm) {
		return dao.create(comm);
	}
	public int update(CommentDTO comm) {
		return dao.update(comm);
	}
	public int delete(int commId) {
		return dao.delete(commId);
	}
	public CommentDTO findComment(int commId) {
		return dao.findComment(commId);
	}
	
	public int findCommentId() {
		return dao.findCommentId();
	}
	public List<CommentDTO> findCommentList(int postId) {
		return dao.findCommentList(postId);
	}
}
