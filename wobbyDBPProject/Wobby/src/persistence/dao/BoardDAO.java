package persistence.dao;


import java.sql.SQLException;
import java.util.List;
import service.dto.BoardDTO;

public interface BoardDAO {
	public BoardDTO create (BoardDTO board);
	
	public int update(BoardDTO board);
	
	public int delete(int boardId);
	
	public BoardDTO findBoard(int boardId);
	
	public List<BoardDTO> findBoardList();
	
	public boolean existingBoard(int boardId);
}
