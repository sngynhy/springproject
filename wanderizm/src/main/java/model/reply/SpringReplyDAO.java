package model.reply;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

class ReplydRowMapper implements RowMapper<ReplyVO> {
	@Override
	public ReplyVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReplyVO vo = new ReplyVO();
		vo.setR_id(rs.getInt("r_id"));
		vo.setB_id(rs.getInt("b_id"));
		vo.setId(rs.getString("id"));
		vo.setReply(rs.getString("reply"));
		vo.setR_date(rs.getString("r_date"));
		return vo;
	}
}

@Repository
public class SpringReplyDAO {
	// 트랜잭션 처리 1. insert/delete 처리 -> userBoard에 update 처리
	private final String insertSQL = "INSERT INTO REPLY (R_ID, B_ID, ID, REPLY) VALUES (REPLY_SEQ.NEXTVAL, ?,?,?)";
	private final String deleteSQL = "DELETE FROM REPLY WHERE R_ID = ?";
	private final String updateBoardSQL = "UPDATE USERBOARD SET R_CNT = R_CNT ? 1 WHERE B_ID = ?";
	private final String updateSQL = "UPDATE REPLY SET REPLY = ? WHERE R_ID = ?";
	private final String getReplySQl = "SELECT R_ID, B_ID, ID, REPLY, TO_CHAR(R_DATE, 'YYYY-MM-DD HH24:MI') R_DATE FROM REPLY WHERE B_ID = ? ORDER BY R_DATE"; // 해당 게시글의 모든 댓글 조회
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void insertReply(ReplyVO vo) { ////// 트랜잭션 처리 필요
		jdbcTemplate.update(insertSQL, vo.getB_id(), vo.getId(), vo.getReply());
//		jdbcTemplate.update(updateBoardSQL, "+", vo.getB_id());
	}
	public void deleteReply(ReplyVO vo) { ////// 트랜잭션 처리 필요
		jdbcTemplate.update(deleteSQL, vo.getR_id());
//		jdbcTemplate.update(updateBoardSQL, "-", vo.getB_id());
	}
	public void updateReply(ReplyVO vo) {
		jdbcTemplate.update(updateSQL, vo.getReply(), vo.getR_id());
	}
	public List<ReplyVO> getReplyList(ReplyVO vo) {
		System.out.println(vo.getB_id());
		Object[] args = {vo.getB_id()};
		return jdbcTemplate.query(getReplySQl, args, new ReplydRowMapper());
	}
}
