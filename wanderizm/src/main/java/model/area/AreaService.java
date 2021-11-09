package model.area;

import java.util.List;

public interface AreaService {
	int insertArea(AreaVO vo);
	int deleteArea(AreaVO vo);
	List<AreaVO> getAreaList();
}
