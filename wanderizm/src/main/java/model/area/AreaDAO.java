package model.area;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AreaDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int insertArea(AreaVO vo) {
		return mybatis.insert("areaDAO.insertArea", vo);
	}
	
	public int deleteArea(AreaVO vo) {
		return mybatis.delete("areaDAO.deleteArea", vo);
	}
	
	public List<AreaVO> getAreaList() {
		return mybatis.selectList("areaDAO.getAreaList");
	}
}
