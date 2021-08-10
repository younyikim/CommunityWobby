package service;

import java.util.List;

import service.dto.BoardDTO;

public interface BoardService {
	public BoardDTO create (BoardDTO board);
	
	public int update(BoardDTO board);
	
	public int delete(int boardId);
	
	public BoardDTO findBoard(int boardId);
	
	public List<BoardDTO> findBoardList();
	
	public boolean existingBoard(String boardId);
}
