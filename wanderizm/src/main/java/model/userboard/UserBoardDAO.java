package model.userboard;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserBoardDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int insertBoard(UserBoardVO vo) {
		return mybatis.insert("userboardDAO.insertBoard", vo);
	}
	
	public int updateBoard(UserBoardVO vo) {
		return mybatis.update("userboardDAO.updateBoard", vo);
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public int deleteBoard(UserBoardVO vo) {
		int res = 0;
		res += mybatis.delete("userboardDAO.deleteBoard", vo); // �Խñ� ����
		res += mybatis.delete("userboardDAO.deleteReply", vo); // ��� ����
		res += mybatis.delete("userboardDAO.deleteLikes", vo); // �� ����
		System.out.println("���� ��� ��  : " + res);
		return res;
	}
	
	public UserBoardVO getBoard(UserBoardVO vo) {
		return (UserBoardVO) mybatis.selectOne("userboardDAO.getBoard", vo);
	}
	
	public List<UserBoardVO> getBoardList(UserBoardVO vo) {
		return mybatis.selectList("userboardDAO.getBoardList", vo);
	}
	
	public int getBoardListCount(UserBoardVO vo) {
		return mybatis.selectOne("userboardDAO.getBoardListCount", vo);
	}
}
