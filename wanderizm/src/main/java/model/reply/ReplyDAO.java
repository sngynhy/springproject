package model.reply;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ReplyDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	@Transactional
	public int insertReply(ReplyVO vo) {
		try {
			mybatis.insert("replyDAO.insertReply", vo);
			mybatis.update("replyDAO.rCntPlus", vo);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Transactional
	public int deleteReply(ReplyVO vo) {
		try {
			mybatis.delete("replyDAO.deleteReply", vo);
			mybatis.update("replyDAO.rCntMinus", vo);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public List<ReplyVO> getReplyList(ReplyVO vo) {
		return mybatis.selectList("replyDAO.getReplyList", vo);
	}
}
