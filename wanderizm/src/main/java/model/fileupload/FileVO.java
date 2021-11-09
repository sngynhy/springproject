package model.fileupload;

import org.springframework.web.multipart.MultipartFile;

public class FileVO {
	
    private String attachPath; // �̹����� ����� ���
    private String Filename; // �����̸�
    private MultipartFile upload;
    private String CKEditorFuncNum;// CKEditor�� �̹��� ÷���Ҷ� ������ ������
                                    // �� ��ҹ��� ������ �����ؼ� ��� modelAttribute�� �ν��ؼ� �޾��� 
	public String getAttachPath() {
		return attachPath;
	}
	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
	}
	public String getFilename() {
		return Filename;
	}
	public void setFilename(String filename) {
		Filename = filename;
	}
	public MultipartFile getUpload() {
		return upload;
	}
	public void setUpload(MultipartFile upload) {
		this.upload = upload;
	}
	public String getCKEditorFuncNum() {
		return CKEditorFuncNum;
	}
	public void setCKEditorFuncNum(String cKEditorFuncNum) {
		CKEditorFuncNum = cKEditorFuncNum;
	}
    
}