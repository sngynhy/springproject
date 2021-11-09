package model.nation;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NationDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int insertNation(NationVO vo) {
		return mybatis.insert("nationDAO.insertNation", vo);
	}
	
	public int deleteNation(NationVO vo) {
		return mybatis.delete("nationDAO.deleteNation", vo);
	}
	
	public List<NationVO> getNationList() {
		return mybatis.selectList("nationDAO.getNationList");
	}
}
