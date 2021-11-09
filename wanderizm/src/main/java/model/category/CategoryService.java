package model.category;

import java.util.List;

public interface CategoryService {
	int insertCategory(CategoryVO vo);
	int deleteCategory(CategoryVO vo);
	List<CategoryVO> getCategoryList();
}
