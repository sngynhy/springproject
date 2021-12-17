package controller.action;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import model.adminboard.AdminBoardService;
import model.adminboard.AdminBoardVO;
import model.fileupload.FileVO;


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
	public String insertAdminBoard(HttpSession session, HttpServletRequest request, AdminBoardVO vo) throws IllegalStateException, IOException {
		vo.setId((String) session.getAttribute("sessionID"));
		
		String imgPath = null;
		String savePath = request.getSession().getServletContext().getRealPath("/images/preview/"); // ���� ����Ǵ� ���
		String loadPath = "/images/preview/"; // ���� ���� ��� - �������� ���Ͽ� �����ϴ� ���, ���� ���丮
		
		System.out.println(" savePath : " + savePath);
		
		MultipartFile fileUpload = vo.getFileUpload(); // ���� ��������
		
		if (!fileUpload.isEmpty()) {
			String originFileNme = fileUpload.getOriginalFilename(); // ���� ���ϸ�
			System.out.println(" originFileName : " + originFileNme);
			
			String ext = FilenameUtils.getExtension(originFileNme); // ���� ������ Ȯ���ڸ�
			String newInfImgFileName = "img_" + UUID.randomUUID() + "." + ext; // ���� �ߺ� ����
			
			imgPath = loadPath + newInfImgFileName; // ���� ���� ���
			vo.setImg_path(imgPath);
			
			System.out.println(" save path : " + savePath + newInfImgFileName);
			System.out.println(" img path : " + imgPath);
			
			File file = new File(savePath + newInfImgFileName); // ���� ����Ǵ� ���� ��� ����
			
			fileUpload.transferTo(file);
			boardService.insertBoard(vo);
		}
		return "redirect:/getAdminBoardList.do";
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
