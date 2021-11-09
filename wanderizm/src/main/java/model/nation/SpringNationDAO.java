package model.nation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

class NationRowMappint implements RowMapper<NationVO> {
	@Override
	public NationVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		NationVO vo = new NationVO();
		vo.setN_id(rs.getString("n_id"));
		vo.setNation(rs.getString("nation"));
		vo.setA_id(rs.getString("a_id"));
		vo.setLat(rs.getDouble("lat"));
		vo.setLng(rs.getDouble("lng"));
		return vo;
	}
}

@Repository
public class SpringNationDAO {
	
	private String getNationListSQL = "SELECT * FROM NATION";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<NationVO> getNationList() {
		return jdbcTemplate.query(getNationListSQL, new NationRowMappint());
	}
}
