package controller.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import model.adminboard.AdminBoardService;
import model.adminboard.AdminBoardVO;


@Controller
public class AdminBoardController {
	
	@Autowired
	private AdminBoardService boardService;
	
	@RequestMapping("/getRecommandList.do")
	public String getList(AdminBoardVO vo, Model model) {
		List<AdminBoardVO> datas = boardService.getBoardList(vo);
		model.addAttribute("datas", datas);
		return "recommand/list.jsp";
	}
	
	@RequestMapping("/getRecommand.do")
	public String getRecommand(HttpSession session, AdminBoardVO vo, Model model) {
		if ((String) session.getAttribute("sessionID") == null) {
			vo.setId("");
		} else {
			vo.setId((String) session.getAttribute("sessionID"));
		}
		model.addAttribute("data", boardService.getBoard(vo));
		return "recommand/view.jsp";
	}
	
	@RequestMapping("/insertRecommandView.do")
	public String getInsertView(Model model) {
		return "recommand/insert.jsp";
	}
	
	@ResponseBody
	@RequestMapping("/insertRecommand.do")
	public String setRecommand(Model model) {
		return "";
	}
	
	@RequestMapping("/updateRecommandView.do")
	public String getUpdateView() {
		return "recommand/insert.jsp";
	}
	
	@ResponseBody
	@RequestMapping("/updateRecommand.do")
	public String updateRecommand(Model model) {
		return "";
	}
	
	@ResponseBody
	@RequestMapping("/deleteRecommand.do")
	public String deleteRecommand() {
		return "true";
	}
}
