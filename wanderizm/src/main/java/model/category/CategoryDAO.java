package model.category;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int insertCategory(CategoryVO vo) {
		return mybatis.insert("categoryDAO.insertCategory", vo);
	}
	
	public int deleteCategory(CategoryVO vo) {
		return mybatis.delete("categoryDAO.deleteCategory", vo);
	}
	
	public List<CategoryVO> getCategoryList() {
		return mybatis.selectList("categoryDAO.getCategoryList");
	}
}
