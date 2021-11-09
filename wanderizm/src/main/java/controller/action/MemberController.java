package controller.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.member.MemberService;
import model.member.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@ResponseBody
	@RequestMapping("/login.do")
	public boolean login(HttpSession session, HttpServletResponse response, MemberVO vo) {
		if (memberService.getMember(vo) != null) { // 로그인 성공
			session.setAttribute("sessionID", vo.getId());
			return true;
		} else { // 로그인 실패
			return false;
		}
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) throws Exception {
		session.invalidate();
		return "index.jsp";
	}
	
	@ResponseBody
	@RequestMapping("/insertMember.do")
	public boolean insertMember(MemberVO vo) throws Exception {
		if (memberService.insertMember(vo) > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@ResponseBody
	@RequestMapping("/checkID.do")
	public boolean checkID(@RequestParam("id") String id, HttpServletResponse response) throws Exception {
		if (memberService.checkID(id) > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping("/mypage.do")
	public String getMember(HttpSession session, MemberVO vo, Model model) {
		vo.setId((String) session.getAttribute("sessionID"));
		model.addAttribute("data", memberService.getMember(vo));
		return "mypage.jsp";
	}
	
	@ResponseBody
	@RequestMapping("/checkPW.do")
	public boolean checkPw(HttpServletResponse response, MemberVO vo, Model model) throws IOException {
		if (memberService.getMember(vo) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping("/updateView.do")
	public String udpateView(HttpSession session, HttpServletResponse response, MemberVO vo, Model model) throws IOException {
		vo.setId((String) session.getAttribute("sessionID"));
		model.addAttribute("data", memberService.getMember(vo));
		return "updateMember.jsp";
	}
	
	@ResponseBody
	@RequestMapping("/updateMember.do")
	public boolean updateMember(HttpSession session, MemberVO vo, Model model) throws IOException {
		vo.setId((String) session.getAttribute("sessionID"));
		System.out.println(vo);
		if (memberService.updateMember(vo) > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@ResponseBody
	@RequestMapping("/updatePw.do")
	public boolean updatePw(HttpSession session, HttpServletResponse response, MemberVO vo, Model model) throws IOException {
		vo.setId((String) session.getAttribute("sessionID")); // 자동 로그아웃 된 상태일 경우를 대비해 sessionID 값의 존재 유무 판단 필요
//		System.out.println(vo);
		if (memberService.updateMember(vo) > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping("/deleteMember.do")
	public String deleteMember(HttpSession session, MemberVO vo) throws Exception {
		vo.setId((String) session.getAttribute("sessionID"));
		memberService.deleteMember(vo);
		session.invalidate();
		return "index.jsp";
	}
}
