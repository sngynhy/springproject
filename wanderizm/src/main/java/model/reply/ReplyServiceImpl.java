package model.reply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("ReplyService")
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyDAO replyDAO;
	
	@Override
	public void insertReply(ReplyVO vo) {
		replyDAO.insertReply(vo);
	}
	@Override
	public void deleteReply(ReplyVO vo) {
		replyDAO.deleteReply(vo);
	}
	@Override
	public List<ReplyVO> getReplyList(ReplyVO vo) {
		return replyDAO.getReplyList(vo);
	}
}
