package persistence.dao;

import java.util.List;

import service.dto.PostDTO;

public interface PostDAO {
	public int create(PostDTO post, int boardId);

	public int update(PostDTO post);

	public int delete(int postId);

	public int increasePostViewCnt(int postId);

	public List<PostDTO> findPopularPostList(int boardId);

	public PostDTO findPost(int postId);

	public List<PostDTO> searchPostList(String keyword);

	public List<PostDTO> findPostList(int boardId);

	public boolean existingPost(int postId);

	public PostDTO findPostInfo(int postId);
	
	public int increaseNumOfScraps(int postId);
	
	 public int decreaseNumOfScraps(int postId);
}
