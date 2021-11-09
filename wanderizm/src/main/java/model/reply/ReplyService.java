package model.reply;

import java.util.List;

public interface ReplyService {
	void insertReply(ReplyVO vo);
	void deleteReply(ReplyVO vo);
	List<ReplyVO> getReplyList(ReplyVO vo);
}
