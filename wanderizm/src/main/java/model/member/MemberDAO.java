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
	
	@Transactional
	public int deleteMember(MemberVO vo) {
		try {
			int res = mybatis.delete("memberDAO.deleteMember", vo);
			res += mybatis.delete("memberDAO.deleteBoard", vo);
			res += mybatis.delete("memberDAO.deleteReply", vo);
			res += mybatis.delete("memberDAO.deleteLikes", vo);
			System.out.println("res : " + res);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
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
				return 0; // 중복 아이디가 없는 경우
			}
		} catch (EmptyResultDataAccessException e) {
			return -1;
		}
	}
}
