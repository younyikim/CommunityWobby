package service;

import java.util.List;

import service.dto.PostDTO;

public interface PostService {
	public PostDTO create(PostDTO post, int boardId);
	
	public int update(PostDTO post);
	
	public int delete(int postId);
	
	public int increasePostViewCnt(int postId);
	
	public List<PostDTO> findPopularPostList(int boardId);
	
	public PostDTO findPost(int postId);
	
	public List<PostDTO> findPostList(int boardId);
	
	public boolean existingPost(int postId);
}
