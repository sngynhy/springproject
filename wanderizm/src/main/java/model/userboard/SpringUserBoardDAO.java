package model.userboard;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

class UserBoardRowMapper implements RowMapper<UserBoardVO> {
	@Override
	public UserBoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserBoardVO vo = new UserBoardVO();
		vo.setB_id(rs.getInt("b_id"));
		vo.setId(rs.getString("id"));
		vo.setTitle(rs.getString("title"));
		vo.setContent(rs.getString("content"));
		vo.setR_cnt(rs.getInt("r_cnt"));
		vo.setLike_cnt(rs.getInt("like_cnt"));
		vo.setB_date(rs.getString("b_date"));
		vo.setB_type(rs.getString("b_type"));
		vo.setA_id(rs.getString("a_id"));
		vo.setN_id(rs.getString("n_id"));
		vo.setCate_id(rs.getString("cate_id"));
		return vo;
	}
}

@Repository
public class SpringUserBoardDAO {
	
	private final String insertSQL = "INSERT INTO USERBOARD (B_ID, ID, TITLE, CONTENT, B_TYPE, A_ID, N_ID, CATE_ID) VALUES (USERBOARD_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
	private final String updateSQL = "UPDATE USERBOARD SET TITLE = ?, CONTENT = ?, U_DATE = SYSDATE, B_TYPE = ?, A_ID = ?,N_ID = ?, CATE_ID = ? WHERE B_ID = ?";
	private final String deleteSQL = "DELETE USERBOARD WHERE B_ID = ?"; // 트랜잭션 처리 -> LIKE 테이블 B_ID로 delete
	private final String deleteLikeSQL = "DELETE FROM LIKES WHERE B_ID = ?";
	private final String getBoardListSQL = "SELECT B_ID, ID, TITLE, CONTENT, R_CNT, LIKE_CNT, TO_CHAR(B_DATE, 'YYYY-MM-DD HH24:MI') B_DATE, B_TYPE, A_ID, N_ID, CATE_ID FROM USERBOARD WHERE B_TYPE = ?";
	private final String getBoardSQL = "SELECT B_ID, ID, TITLE, CONTENT, R_CNT, LIKE_CNT, TO_CHAR(B_DATE, 'YYYY-MM-DD HH24:MI') B_DATE, B_TYPE, A_ID, N_ID, CATE_ID FROM USERBOARD WHERE B_ID = ? ORDER BY B_DATE DESC";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int insertBoard(UserBoardVO vo) {
//		System.out.println(vo);
//		System.out.println("여기");
		Object[] args = {vo.getId(), vo.getTitle(), vo.getContent(), vo.getB_type(), vo.getA_id(), vo.getN_id(), vo.getCate_id()};
		return jdbcTemplate.update(insertSQL, args);
	}
	
	public int updateBoard(UserBoardVO vo) {
		Object[] args = {vo.getTitle(), vo.getContent(), vo.getB_type(), vo.getA_id(), vo.getN_id(), vo.getCate_id(), vo.getB_id()};
		return jdbcTemplate.update(updateSQL, args);
	}
	
	public int deleteBoard(UserBoardVO vo) { //////////////////// 트랜잭션 처리 필요
		return jdbcTemplate.update(deleteSQL, vo.getB_id());
//		jdbcTemplate.update(deleteLikeSQL, vo.getB_id());
	}
	
	public List<UserBoardVO> getBoardList(UserBoardVO vo) {
		
		String sql = "";
		ArrayList<String> params = new ArrayList<String>();
		int size = 0; // args 배열 길이
		
		if (vo.getB_type().equals("info")) { // 정보 공유 게시글
			sql += getBoardListSQL + " AND CATE_ID = ?";
			params.add(vo.getB_type());
			params.add(vo.getCate_id());
			if (vo.getKeyword() != null) {
				if (vo.getCondition().equals("title")) {
					sql += " AND TITLE LIKE ?";
				} else if (vo.getCondition().equals("content")) {
					sql += " AND CONTENT LIKE ?";
				} else if (vo.getCondition().equals("id")) {
					sql += " AND ID LIKE ?";
				}
				params.add("%"+vo.getKeyword()+"%");
			}
		} else if (vo.getB_type().equals("ask")) { // 자유 질문 게시글
			sql += getBoardListSQL;
			params.add(vo.getB_type());
			if (vo.getKeyword() != null) {
				if (vo.getCondition().equals("title")) {
					sql += " AND TITLE LIKE ?";
				} else if (vo.getCondition().equals("content")) {
					sql += " AND CONTENT LIKE ?";
				} else if (vo.getCondition().equals("id")) {
					sql += " AND ID LIKE ?";
				}
				params.add("%"+vo.getKeyword()+"%");
			}
		} else if (vo.getB_type().equals("review")) { // 여행 후기 게시글
			sql += getBoardListSQL + " AND N_ID = ?";
			params.add(vo.getB_type());
			params.add(vo.getN_id());
			if (vo.getKeyword() != null) {
				if (vo.getCondition().equals("title")) {
					sql += " AND TITLE LIKE ?";
				} else if (vo.getCondition().equals("content")) {
					sql += " AND CONTENT LIKE ?";
				} else if (vo.getCondition().equals("id")) {
					sql += " AND ID LIKE ?";
				}
				params.add("%"+vo.getKeyword()+"%");
			}
		}
		
		sql += " ORDER BY B_DATE DESC";
		System.out.println(sql);
		Object[] args = new Object[params.size()];
		for (String temp : params) {
			args[size++] = temp;
		}
		System.out.println(sql);
		for(int i=0; i<args.length; i++) {
			System.out.println(args[i]);
		}
		return jdbcTemplate.query(sql, args, new UserBoardRowMapper());
	}

	public UserBoardVO getBoard(UserBoardVO vo) { // 선택한 글 보기
		Object[] args = {vo.getB_id()};
		try {
			return jdbcTemplate.queryForObject(getBoardSQL, args, new UserBoardRowMapper());
		} catch (Exception e) {
			return null;
		}
		
	}
}
