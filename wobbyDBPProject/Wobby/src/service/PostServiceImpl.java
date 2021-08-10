package service;

import java.util.List;

import persistence.DAOFactory;
import persistence.dao.ClubDAO;
import persistence.dao.PostDAO;
import service.dto.PostDTO;

public class PostServiceImpl {
	private PostDAO dao = null;
	
	public PostServiceImpl() {
		DAOFactory factory = new DAOFactory();
		dao = factory.getPostDAO();
	}
	public int create(PostDTO post,int boardId) {
		return dao.create(post, boardId);
	}
	
	public int update(PostDTO post) {
		return dao.update(post);
	}
	
	public int delete(int postId) {
		return dao.delete(postId);
	}
	
	public int increasePostViewCnt(int postId) {
		return dao.increasePostViewCnt(postId);
	}
	
	public List<PostDTO> findPopularPostList(int boardId) {
		return dao.findPopularPostList(boardId);
	}
	public PostDTO findPost(int postId) {
		return dao.findPost(postId);
	}
	
	public List<PostDTO> findPostList(int boardId) {
		return dao.findPostList(boardId);
	}
	
	public boolean existingPost(int postId) {
		return dao.existingPost(postId);
	}
}
