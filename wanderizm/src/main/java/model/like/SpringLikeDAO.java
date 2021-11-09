package model.like;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SpringLikeDAO {
	
	private String insertSQL = "INSERT INTO LIKES VALUES (?,?)";
	private String deleteSQL = "DELETE FROM LIKES WHERE ID = ?";
	private String likeCntPlus = "UPDATE USERBOARD SET LIKE_CNT = LIKE_CNT + 1 WHERE B_ID = ?"; // userboard or adminboard
	private String likeCntMinus = "UPDATE USERBOARD SET LIKE_CNT = LIKE_CNT - 1 WHERE B_ID = ?"; // userboard or adminboard
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int insertLike(LikeVO vo) { // 트랜잭션 필요
		try {
			jdbcTemplate.update(insertSQL, vo.getB_id(), vo.getId());
			jdbcTemplate.update(likeCntPlus, vo.getB_id());
			return 1;
		} catch(Exception e) {
			return 0;
		}
	}
	public int deleteLike(LikeVO vo) { // 트랜잭션 필요
		try {
			jdbcTemplate.update(deleteSQL, vo.getId());
			jdbcTemplate.update(likeCntMinus, vo.getB_id());
			return 1;
		} catch(Exception e) {
			return 0;
		}
	}
}
