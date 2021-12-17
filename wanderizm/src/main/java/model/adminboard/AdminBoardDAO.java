package model.adminboard;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AdminBoardDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;	
	
	public int insertBoard(AdminBoardVO vo) {
		return mybatis.insert("adminboardDAO.insertBoard", vo);
	}
	
	public int updateBoard(AdminBoardVO vo) {
		return mybatis.update("adminboardDAO.updateBoard", vo);
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public int deleteBoard(AdminBoardVO vo) {
		int res = 0;
		res += mybatis.delete("adminboardDAO.deleteBoard", vo);
		res += mybatis.delete("adminboardDAO.deleteLikes", vo);
		System.out.println("실행 결과 수  : " + res);
		return res;
	}
	
	public AdminBoardVO getBoard(AdminBoardVO vo) {
		return (AdminBoardVO) mybatis.selectOne("adminboardDAO.getBoard", vo);
	}
	
	public List<AdminBoardVO> getBoardList(AdminBoardVO vo) {
		return mybatis.selectList("adminboardDAO.getBoardList", vo);
	}
}
