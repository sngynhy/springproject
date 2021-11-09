package model.member;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

class MemberRowMapper implements RowMapper<MemberVO> {
	@Override
	public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberVO outvo = new MemberVO();
		outvo.setId(rs.getString("id"));
		outvo.setPw(rs.getString("pw"));
		outvo.setEmail(rs.getString("email"));
		outvo.setPhone(rs.getString("phone"));
		return outvo;
	}
}

@Repository
public class SpringMemberDAO {
	
	// ȸ������, �α���, ���� ����, Ż��, ID �ߺ�üũ
	private String insertSQL = "insert into member values (?,?,?,?)";
	private String getMemberSQL = "select * from member where id = ? and pw = ?";
	private String updateSQL = "update member set pw = ?, email = ?, phone = ? where id = ?";
	private String deleteSQL = "delete from member where id = ?"; // ȸ�� Ż�� �� member, reply, likes, userboard ���̺��� �ش� ȸ�� ID ������ ����
	private String checkID = "select * from member where id = ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int insertMember(MemberVO vo) {
		return jdbcTemplate.update(insertSQL, vo.getId(), vo.getPw(), vo.getEmail(), vo.getPhone());
	}
	
	public MemberVO getMember(MemberVO vo) {	
		try {
			if (vo.getPw() == null) { // id�� �̿��Ͽ� ȸ�� ���� ��ȸ - ����������
//				System.out.println("����������");
				Object[] args = {vo.getId()};
				return jdbcTemplate.queryForObject(getMemberSQL, args, new MemberRowMapper());
			} else { // id, pw�� ȸ�� ��ȸ - �α���
				System.out.println("�α���");
				String sql = getMemberSQL + " and pw = ?";
				Object[] args = {vo.getId(), vo.getPw()};
				return jdbcTemplate.queryForObject(sql, args, new MemberRowMapper());
			}
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	public int updateMember(MemberVO vo) {
		return jdbcTemplate.update(updateSQL, vo.getPw(), vo.getEmail(), vo.getPhone(), vo.getId());
	}
	public int deleteMember(MemberVO vo) { ////// Ʈ����� ó�� �ʿ�
		return jdbcTemplate.update(deleteSQL, vo.getId());
//		return jdbcTemplate.update(deleteSQL, "member", vo.getId());
//		jdbcTemplate.update(deleteSQL, "reply", vo.getId());
//		jdbcTemplate.update(deleteSQL, "likes", vo.getId());
//		jdbcTemplate.update(deleteSQL, "userboard", vo.getId());
	}
	
	public boolean checkID(String id) {
		Object[] args = {id};
		try {
			jdbcTemplate.queryForObject(checkID, args, new MemberRowMapper());
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}
}
