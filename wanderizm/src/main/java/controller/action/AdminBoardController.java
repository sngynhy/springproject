package controller.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import model.adminboard.AdminBoardService;
import model.adminboard.AdminBoardVO;


@Controller
public class AdminBoardController {
	
	@Autowired
	private AdminBoardService boardService;
	
	@RequestMapping("/getAdminBoardList.do")
	public String getAdminBoardList(AdminBoardVO vo, Model model) {
		List<AdminBoardVO> datas = boardService.getBoardList(vo);
		model.addAttribute("datas", datas);
		return "/jsp-adminboard/list.jsp";
	}
	
	@RequestMapping("/getAdminBoard.do")
	public String getAdminBoard(HttpSession session, AdminBoardVO vo, Model model) {
		if ((String) session.getAttribute("sessionID") == null) {
			vo.setId("");
		} else {
			vo.setId((String) session.getAttribute("sessionID"));
		}
		model.addAttribute("data", boardService.getBoard(vo));
		return "/jsp-adminboard/view.jsp";
	}

	@RequestMapping("/insertAdminBoard.do")
	public String insertAdminBoard(HttpSession session, AdminBoardVO vo) {
		vo.setId((String) session.getAttribute("sessionID"));
		boardService.insertBoard(vo);
		return "";
	}
	
	@RequestMapping("/updateAdminBoardView.do")
	public String getUpdateView() {
		return "/jsp-adminboard/insert.jsp";
	}
	
	@ResponseBody
	@RequestMapping("/updateAdminBoard.do")
	public String updateAdminBoard(Model model) {
		return "";
	}
	
	@ResponseBody
	@RequestMapping("/deleteAdminBoard.do")
	public String deleteAdminBoard() {
		return "true";
	}
}
