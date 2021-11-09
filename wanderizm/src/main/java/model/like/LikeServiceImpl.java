package model.like;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("LikeService")
public class LikeServiceImpl implements LikeService {
	
	@Autowired
	private LikeDAO likeDAO;
	
	@Override
	public int insertLike(LikeVO vo) {
		return likeDAO.insertLike(vo);
	}
	@Override
	public int deleteLike(LikeVO vo) {
		return likeDAO.deleteLike(vo);
	}
}
