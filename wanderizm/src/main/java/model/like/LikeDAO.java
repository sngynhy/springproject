package model.like;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class LikeDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	@Transactional
	public int insertLike(LikeVO vo) {
		try {
			mybatis.insert("likeDAO.insertLike", vo);
			mybatis.update("likeDAO.likeCntPlus", vo);
			return 1;
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@Transactional
	public int deleteLike(LikeVO vo) {
		try {
			mybatis.delete("likeDAO.deleteLike", vo);
			mybatis.update("likeDAO.likeCntMinus", vo);
			return 1;
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
}
