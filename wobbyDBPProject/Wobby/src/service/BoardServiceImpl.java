package service;

import java.util.List;

import persistence.DAOFactory;
import persistence.dao.BoardDAO;
import service.dto.BoardDTO;

public class BoardServiceImpl {
	private BoardDAO dao = null;
	
	public BoardServiceImpl() {
		DAOFactory factory = new DAOFactory();
		dao = factory.getBoardDAO();
	}
	public BoardDTO create (BoardDTO board) {
		return dao.create(board);
	}
	
	public int update(BoardDTO board) {
		return dao.update(board);
	}
	
	public int delete(int boardId) {
		return dao.delete(boardId);
	}
	
	public BoardDTO findBoard(int boardId) {
		return dao.findBoard(boardId);
	}
	
	public List<BoardDTO> findBoardList(){
		return dao.findBoardList();
	}
	
	public boolean existingBoard(int boardId) {
		return dao.existingBoard(boardId);
	}
}
