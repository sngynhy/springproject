package controller.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	/*public String insertAdminBoard(HttpSession session, AdminBoardVO vo, MultipartHttpServletRequest request) {|*/
	public String insertAdminBoard(HttpSession session, MultipartHttpServletRequest request) throws JsonProcessingException {
//		vo.setId((String) session.getAttribute("sessionID"));
		
//		// ����� �̹��� ���� ���
//		String savePath = request.getSession().getServletContext().getRealPath("/image/preview/");
//		System.out.println("savePath : " + savePath);
		
		System.out.println("title : " + request.getParameter("title"));
		
		List<MultipartFile> fileList = request.getFiles("upload"); // ���� ��������
		System.out.println();
		
		
		HashMap<String, Object> res = new HashMap<String, Object>();
		ObjectMapper mappper = new ObjectMapper();
		String jsonStr = "";
		
		String imgPath = null;
		
		System.out.println(" path : " + request.getSession().getServletContext().getRealPath("images/upload"));
		
		String savePath = request.getSession().getServletContext().getRealPath("/images/upload/"); // ���� ����Ǵ� ���
		String loadPath = "/images/upload/"; // ���� ���� ��� - �������� ���Ͽ� �����ϴ� ���, ���� ���丮
		
		System.out.println("load path : "+ loadPath);
		
		String filepath = "";
		
		for(MultipartFile mf : fileList) {
			System.out.println(" size : " + fileList.get(0).getSize());
			if (fileList.get(0).getSize() > 0) {
				String originFileNme = mf.getOriginalFilename(); // ���� ���ϸ�
				System.out.println(" originFileNme : " + originFileNme);
				
				String ext = FilenameUtils.getExtension(originFileNme); // ���� ������ Ȯ���ڸ�
				String newInfImgFileName = "img_" + UUID.randomUUID() + "." + ext; // ���ϸ� �ߺ� ����
				
				imgPath = loadPath + newInfImgFileName; // ���� ���� ���
				
				System.out.println(" save path : " + savePath + newInfImgFileName);
				System.out.println(" load path : " + imgPath);
				
				File file = new File(savePath + newInfImgFileName); // ���� ����Ǵ� ���� ��� ����
				filepath = savePath + newInfImgFileName;
				try {
					mf.transferTo(file); // ���� ���Ϸ� �̵�
				} catch (IllegalStateException e) {
					System.out.println("IllegalStateException");
					e.printStackTrace();
					System.out.println("@@ false");
				} catch (IOException e) {
					System.out.println("IOException");
					e.printStackTrace();
					System.out.println("@@ false");
				}
				
			}
		}
		
		System.out.println("@@ file " + filepath);
		
		return "";
//		boardService.insertBoard(vo);
//		return "redirect:/getBoardList.do";
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
