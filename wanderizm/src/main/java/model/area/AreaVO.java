package model.area;

import java.io.Serializable;

public class AreaVO implements Serializable{
	private String a_id; // pk
	private String area; // Áö¿ª - EU µî
	
	public String getA_id() {
		return a_id;
	}
	public void setA_id(String a_id) {
		this.a_id = a_id;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
}
