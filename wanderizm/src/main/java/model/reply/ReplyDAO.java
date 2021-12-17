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
	
	@Transactional(rollbackFor = {Exception.class})
	public int insertReply(ReplyVO vo) {
		int res = 0;
		res += mybatis.insert("replyDAO.insertReply", vo);
		res += mybatis.update("replyDAO.rCntPlus", vo);
		System.out.println("실행 결과 수  : " + res);
		return res;
	}
	@Transactional(rollbackFor = {Exception.class})
	public int deleteReply(ReplyVO vo) {
		int res = 0;
		res += mybatis.delete("replyDAO.deleteReply", vo);
		res += mybatis.update("replyDAO.rCntMinus", vo);
		System.out.println("실행 결과 수  : " + res);
		return res;
	}
	
	public List<ReplyVO> getReplyList(ReplyVO vo) {
		return mybatis.selectList("replyDAO.getReplyList", vo);
	}
}
