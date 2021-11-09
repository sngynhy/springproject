package controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import model.area.AreaService;
import model.area.AreaVO;
import model.category.CategoryService;
import model.category.CategoryVO;
import model.like.LikeService;
import model.like.LikeVO;
import model.nation.NationService;
import model.nation.NationVO;
import model.reply.ReplyService;
import model.reply.ReplyVO;
import model.userboard.PaginationVO;
import model.userboard.UserBoardService;
import model.userboard.UserBoardVO;

@Controller
@SessionAttributes("sidebarData")
public class UserBoardController {
	
	@Autowired
	private UserBoardService boardService;
	@Autowired
	private CategoryService categoryService; // 교통, 숙소, 맛집 등
	@Autowired
	private NationService nationService; // 파리, 영국, 이탈리아 등
	@Autowired
	private AreaService areaService; // 유럽, 아시아 등
	@Autowired
	private ReplyService replyService; // 댓글
	@Autowired
	private LikeService likeService; // 찜
	
	@ModelAttribute("sidebarData")
	public Model getSideBarData(Model model) {
		List<CategoryVO> cateData = categoryService.getCategoryList();
		model.addAttribute("cateData", cateData);
		List<NationVO> nationData = nationService.getNationList();
		model.addAttribute("nationData", nationData);
		List<AreaVO> areaData = areaService.getAreaList();
		model.addAttribute("areaDate", areaData);
		return model;
	}
	
	@RequestMapping("/main.do")
	public String main(Model model) {
		return "main.jsp";
	}
	
	@RequestMapping("/getBoardList.do")
	public String getBoardList(UserBoardVO vo, @RequestParam(defaultValue="1") int curPage, Model model) throws Exception {
		
		int listCnt = boardService.getBoardListCount(vo); // 전체 게시글 수
//		System.out.println("전체 게시글 수 : " + listCnt);
		PaginationVO pagination = new PaginationVO(listCnt, curPage);
		vo.setStartIndex(pagination.getStartIndex()); // 게시글 시작 index
		vo.setCntPerPage(vo.getStartIndex() + pagination.getPageSize());
		
//		System.out.println("start index : "+ vo.getStartIndex());
//		System.out.println("cntPerPage : " + vo.getCntPerPage());
		
		List<UserBoardVO> datas = boardService.getBoardList(vo); // 현재 페이지 게시글 목록 조회 - 10개씩 
		
		model.addAttribute("datas", datas); // 정보 저장 - setAttribute의 역할
		model.addAttribute("b_type", vo.getB_type());
		model.addAttribute("cate_id", vo.getCate_id());
		model.addAttribute("n_id", vo.getN_id());
		model.addAttribute("pagination", pagination);
		return "getBoardList.jsp";
	}
	
	@RequestMapping("/getBoard.do")
	public String getBoard(HttpSession session, UserBoardVO vo, ReplyVO rvo, Model model) {
		if ((String) session.getAttribute("sessionID") == null) {
			vo.setId("");
		} else {
			vo.setId((String) session.getAttribute("sessionID"));
		}
		model.addAttribute("data", boardService.getBoard(vo));
		model.addAttribute("rdata", replyService.getReplyList(rvo));
		return "readBoard.jsp";
	}
	
	@RequestMapping("/insertBoardView.do")
	public String insertView(HttpSession session, UserBoardVO vo, Model model) {
		vo.setId((String) session.getAttribute("sessionID"));
		model.addAttribute("data", vo);
		return "insertBoard.jsp";
	}
	
	@RequestMapping("/insertBoard.do")
	public String insertBoard(HttpSession session, UserBoardVO vo) {
		vo.setId((String) session.getAttribute("sessionID"));
		boardService.insertBoard(vo);
		return "redirect:getBoardList.do?b_type=" + vo.getB_type() + "&cate_id=" + vo.getCate_id() + "&a_id=" + vo.getA_id() + "&n_id=" + vo.getN_id();
	}
	
	@RequestMapping("/updateboardView.do")
	public String updateView(HttpSession session, UserBoardVO vo, Model model) {
		vo.setId((String) session.getAttribute("sessionID"));
		model.addAttribute("data", boardService.getBoard(vo));
		return "updateBoard.jsp";
	}
	
	@RequestMapping("/updateBoard.do")
	public String updateBoard(UserBoardVO vo, Model model) {
		boardService.updateBoard(vo);
		return "redirect:getBoard.do?b_id=" + vo.getB_id();
	}
	
	
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(UserBoardVO vo, Model model) throws IOException {
		boardService.deleteBoard(vo);
		return "redirect:getBoardList.do?b_type=" + vo.getB_type() + "&cate_id=" + vo.getCate_id() + "&a_id=" + vo.getA_id() + "&n_id=" + vo.getN_id();
	}
	
	@ResponseBody
	@RequestMapping("/insertLike.do")
	public String insertLike(LikeVO vo) {
		String result = "false";
		if(likeService.insertLike(vo) > 0) {
//			System.out.println(" insert res :" + likeService.insertLike(vo));
			result = "true";
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/deleteLike.do")
	public String deleteLike(LikeVO vo) {
		String result = "false";
		if(likeService.deleteLike(vo) > 0) {
//			System.out.println(" delete res :" + likeService.deleteLike(vo));
			result = "true";
		}
		return result;
	}
}
