package model.like;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class LikeDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	@Transactional(rollbackFor = {Exception.class})
	public int insertLike(LikeVO vo) {
		int res = 0;
		res += mybatis.insert("likeDAO.insertLike", vo);
		res += mybatis.update("likeDAO.likeCntPlus", vo);
		System.out.println("실행 결과 수  : " + res);
		return res;
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public int deleteLike(LikeVO vo) {
		int res = 0;
		res += mybatis.delete("likeDAO.deleteLike", vo);
		res += mybatis.update("likeDAO.likeCntMinus", vo);
		System.out.println("실행 결과 수  : " + res);
		return res;
	}
}
