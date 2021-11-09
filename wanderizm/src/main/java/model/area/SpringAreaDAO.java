package model.area;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

class areaRowMapping implements RowMapper<AreaVO> {
	@Override
	public AreaVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		AreaVO vo = new AreaVO();
		vo.setA_id(rs.getString("a_id"));
		vo.setArea(rs.getString("area"));
		return vo;
	}
}

@Repository
public class SpringAreaDAO {
	
	private String getAreaListSQL = "SELECT * FROM AREA";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<AreaVO> getAreaList() {
		return jdbcTemplate.query(getAreaListSQL, new areaRowMapping());
	}
}
