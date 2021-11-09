package model.category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

class categoryRowMapper implements RowMapper<CategoryVO> {
	@Override
	public CategoryVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CategoryVO vo = new CategoryVO();
		vo.setCate_id(rs.getString("cate_id"));
		vo.setCategory(rs.getString("category"));
		return vo;
	}
}

@Repository
public class SpringCategoryDAO {
	
	private String getCategoryListSQL = "SELECT * FROM CATEGORY";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<CategoryVO> getCategoryList() {
		return jdbcTemplate.query(getCategoryListSQL, new categoryRowMapper());
	}
}
