package model.userboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userboardService")
public class UserBoardServiceImpl implements UserBoardService {
	
	@Autowired // 의존성 주입
	private UserBoardDAO boardDAO;
	
	@Override
	public int insertBoard(UserBoardVO vo) {
		return boardDAO.insertBoard(vo);
	}
	@Override
	public int updateBoard(UserBoardVO vo) {
		return boardDAO.updateBoard(vo);
	}
	@Override
	public int deleteBoard(UserBoardVO vo) {
		return boardDAO.deleteBoard(vo);
	}
	@Override
	public List<UserBoardVO> getBoardList(UserBoardVO vo) {
		return boardDAO.getBoardList(vo);
	}
	@Override
	public UserBoardVO getBoard(UserBoardVO vo) {
		return boardDAO.getBoard(vo);
	}
	@Override
	public int getBoardListCount(UserBoardVO vo) {
		return boardDAO.getBoardListCount(vo);
	}
}
