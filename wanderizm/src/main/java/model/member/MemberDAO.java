package model.member;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int insertMember(MemberVO vo) {
		return mybatis.insert("memberDAO.insertMember", vo);
	}
	
	public int updateMember(MemberVO vo) {
		return mybatis.update("memberDAO.updateMember", vo);
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public int deleteMember(MemberVO vo) {
		int res = 0;
		res += mybatis.delete("memberDAO.deleteMember", vo);
		res += mybatis.delete("memberDAO.deleteBoard", vo);
		res += mybatis.delete("memberDAO.deleteReply", vo);
		res += mybatis.delete("memberDAO.deleteLikes", vo);
		System.out.println("���� ��� ��  : " + res);
		return res;
	}
	
	public MemberVO getMember(MemberVO vo) {
		return (MemberVO) mybatis.selectOne("memberDAO.getMember", vo);
	}

	public int checkID(String id) {
		try {
			int res = mybatis.selectOne("memberDAO.checkID", id);
			if (res > 0) {
				return 1;
			} else {
				return 0; // �ߺ� ���̵� ���� ���
			}
		} catch (EmptyResultDataAccessException e) {
			return -1;
		}
	}
}
