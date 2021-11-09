package model.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public int insertMember(MemberVO vo) {
		return memberDAO.insertMember(vo);
	}
	@Override
	public int checkID(String id) {
		return memberDAO.checkID(id);
	}
	@Override
	public MemberVO getMember(MemberVO vo) {
		return memberDAO.getMember(vo);
	}
	@Override
	public int updateMember(MemberVO vo) {
		return memberDAO.updateMember(vo);
	}
	@Override
	public int deleteMember(MemberVO vo) {
		return memberDAO.deleteMember(vo);
	}
}
