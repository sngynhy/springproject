package model.category;

import java.io.Serializable;

public class CategoryVO implements Serializable { // Serializable�� ��ӹ޾� NotSerializableException �߻� �ذ�
	
	private String cate_id;
	private String category;
	
	public String getCate_id() {
		return cate_id;
	}
	public void setCate_id(String cate_id) {
		this.cate_id = cate_id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "CategoryVO [cate_id=" + cate_id + ", category=" + category + "]";
	}
	
}
