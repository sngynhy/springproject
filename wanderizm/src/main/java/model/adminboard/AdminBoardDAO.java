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
	
	@Transactional
	public int deleteBoard(AdminBoardVO vo) {
		try {
			int res = mybatis.delete("adminboardDAO.deleteBoard", vo); // 게시글 삭제
			res += mybatis.delete("adminboardDAO.deleteLikes", vo); // 찜 삭제
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public AdminBoardVO getBoard(AdminBoardVO vo) {
		return (AdminBoardVO) mybatis.selectOne("adminboardDAO.getBoard", vo);
	}
	
	public List<AdminBoardVO> getBoardList(AdminBoardVO vo) {
		return mybatis.selectList("adminboardDAO.getBoardList", vo);
	}
}
