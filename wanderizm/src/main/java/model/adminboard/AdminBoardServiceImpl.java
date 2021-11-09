package model.adminboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminboardService")
public class AdminBoardServiceImpl implements AdminBoardService {
	
	@Autowired
	private AdminBoardDAO boardDAO;
	
	@Override
	public int insertBoard(AdminBoardVO vo) {
		return boardDAO.insertBoard(vo);
	}
	
	@Override
	public int updateBoard(AdminBoardVO vo) {
		return boardDAO.updateBoard(vo);
	}
	@Override
	public int deleteBoard(AdminBoardVO vo) {
		return boardDAO.deleteBoard(vo);
	}
	@Override
	public AdminBoardVO getBoard(AdminBoardVO vo) {
		return boardDAO.getBoard(vo);
	}
	@Override
	public List<AdminBoardVO> getBoardList(AdminBoardVO vo) {
		return boardDAO.getBoardList(vo);
	}
}
