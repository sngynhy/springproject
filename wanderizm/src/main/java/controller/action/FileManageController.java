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
		
		List<MultipartFile> fileList = request.getFiles("upload"); // 파일 가져오기
		
		HashMap<String, Object> res = new HashMap<String, Object>(); // 업로드 결과, 이미지 저장 경로 저장할 맵
		ObjectMapper mappper = new ObjectMapper();
		String jsonStr = ""; // 맵에 저장되어 있는 데이터를  json 형식의 string 타입으로 변환하여 저장할 변수
		
		String imgPath = null;
		
		System.out.println(" path : " + request.getSession().getServletContext().getRealPath("images/upload"));
		
		String savePath = request.getSession().getServletContext().getRealPath("/images/upload/"); // 실제 저장되는 경로
		String loadPath = "/images/upload/"; // 서버 상의 경로 - 서버에서 파일에 접근하는 경로, 저장 디렉토리
		
		for (MultipartFile mf : fileList) {
			System.out.println(mf);
			System.out.println(" size : " + fileList.get(0).getSize());
			if (fileList.get(0).getSize() > 0) {
				String originFileNme = mf.getOriginalFilename(); // 실제 파일명
				System.out.println(" originFileNme : " + originFileNme);
				
				String ext = FilenameUtils.getExtension(originFileNme); // 실제 파일의 확장자명
				String newInfImgFileName = "img_" + UUID.randomUUID() + "." + ext; // 파일명 중복 방지
				
				imgPath = loadPath + newInfImgFileName; // 파일 접근 경로
				
				System.out.println(" save path : " + savePath + newInfImgFileName);
				System.out.println(" load path : " + imgPath);
				
				File file = new File(savePath + newInfImgFileName); // 실제 저장되는 파일 경로 지정
				
				try {
					mf.transferTo(file); // 실제 파일로 이동
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
					jsonStr = mappper.writeValueAsString(res); // writeValueAsString : 자바스크립트에 json을 넘겨줄 때 사용
					return jsonStr;
				}
				
			}
		}

		res.put("uploaded", true);
		res.put("url", imgPath);
	
		jsonStr = mappper.writeValueAsString(res);
		System.out.println(jsonStr);
		return jsonStr;
	}

}