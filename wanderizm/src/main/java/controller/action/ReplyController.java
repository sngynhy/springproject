package controller.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import model.reply.ReplyService;
import model.reply.ReplyVO;
import model.userboard.UserBoardVO;

@Controller
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	@RequestMapping("/insertReply.do")
	public String insertReply(ReplyVO rvo, UserBoardVO uvo) { /// 飘罚黎记 贸府
//		System.out.println(rvo);
//		System.out.println(uvo);
		replyService.insertReply(rvo);
		return "redirect:getBoard.do?b_id=" + rvo.getB_id() + "&b_type=" + uvo.getB_type() + "&cate_id=" + uvo.getCate_id() + "&a_id=" + uvo.getA_id() + "&n_id=" + uvo.getN_id();
	}
	@RequestMapping("/deleteReply.do")
	public String deleteReply(ReplyVO rvo, UserBoardVO uvo) { /// 飘罚黎记 贸府
		replyService.deleteReply(rvo);
		return "redirect:getBoard.do?b_id=" + rvo.getB_id() + "&b_type=" + uvo.getB_type() + "&cate_id=" + uvo.getCate_id() + "&a_id=" + uvo.getA_id() + "&n_id=" + uvo.getN_id();
	}
}
