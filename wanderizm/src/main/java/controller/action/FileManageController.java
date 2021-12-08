package controller.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class FileManageController {
	
	@ResponseBody
	@RequestMapping(value="/fileupload.do")
	public String uploadFile (@RequestParam Map<String, Object> map, MultipartHttpServletRequest request) throws JsonProcessingException {
		
		List<MultipartFile> fileList = request.getFiles("upload"); // ���� ��������
		
		HashMap<String, Object> res = new HashMap<String, Object>();
		ObjectMapper mappper = new ObjectMapper();
		String jsonStr = "";
		
		String imgPath = null;
		
//		System.out.println(" path : " +request.getSession().getServletContext().getRealPath("images/upload"));
		
		String savePath = request.getSession().getServletContext().getRealPath("images/upload/"); // ���� ����Ǵ� ���
		String loadPath = "/images/upload/"; // ���� ���� ��� - �������� ���Ͽ� �����ϴ� ���, ���� ���丮
		
		for(MultipartFile mf : fileList) {
//			System.out.println(" size : "+ fileList.get(0).getSize());
			if (fileList.get(0).getSize() > 0) {
				String originFileNme = mf.getOriginalFilename(); // ���� ���ϸ�
//				System.out.println(" originFileNme : " + originFileNme);
				
				String ext = FilenameUtils.getExtension(originFileNme); // ���� ������ Ȯ���ڸ�
				String newInfImgFileName = "img_" + UUID.randomUUID() + "." + ext; // ���ϸ� �ߺ� ����
				
				imgPath = loadPath + newInfImgFileName; // ���� ���� ���
				
//				System.out.println(" save path : " + savePath + newInfImgFileName);
//				System.out.println(" load path : " + imgPath);
				
				File file = new File(savePath + newInfImgFileName); // ���� ����Ǵ� ���� ��� ����
				
				try {
					mf.transferTo(file); // ���� ���Ϸ� �̵�
				} catch (IllegalStateException e) {
					System.out.println("IllegalStateException");
					e.printStackTrace();
					res.put("uploaded", false);
					jsonStr = mappper.writeValueAsString(res);
					return jsonStr;
				} catch (IOException e) {
					System.out.println("IOException");
					e.printStackTrace();
					res.put("uploaded", false);
					jsonStr = mappper.writeValueAsString(res);
					return jsonStr;
				}
				
			}
		}

		res.put("uploaded", true);
		res.put("url", imgPath);
	
		jsonStr = mappper.writeValueAsString(res);
		
		return jsonStr;
	}

}