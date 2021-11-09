package model.adminboard;

public class AdminBoardVO {

	private int b_id;
	private String title;
	private String content;
	private int like_cnt;
	private String b_date;
	private String u_date;
	private String id;
	private String img_path; // ½æ³×ÀÏ ÀÌ¹ÌÁö °æ·Î
	private int fav; // Âò ±â·Ï - Âò ¾ÈÇÑ °æ¿ì 0, ÂòÇÑ °æ¿ì 1
	
	public int getB_id() {
		return b_id;
	}
	public void setB_id(int b_id) {
		this.b_id = b_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getLike_cnt() {
		return like_cnt;
	}
	public void setLike_cnt(int like_cnt) {
		this.like_cnt = like_cnt;
	}
	public String getB_date() {
		return b_date;
	}
	public void setB_date(String b_date) {
		this.b_date = b_date;
	}
	public String getU_date() {
		return u_date;
	}
	public void setU_date(String u_date) {
		this.u_date = u_date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getFav() {
		return fav;
	}
	public void setFav(int fav) {
		this.fav = fav;
	}
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	
	@Override
	public String toString() {
		return "AdminBoardVO [b_id=" + b_id + ", title=" + title + ", content=" + content + ", like_cnt=" + like_cnt
				+ ", b_date=" + b_date + ", u_date=" + u_date + ", id=" + id + ", img_path=" + img_path + ", fav=" + fav
				+ "]";
	}
}
