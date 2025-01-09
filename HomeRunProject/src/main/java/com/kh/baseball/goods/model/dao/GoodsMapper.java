package com.kh.baseball.goods.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.baseball.goods.model.vo.Goods;
import com.kh.baseball.goods.model.vo.GoodsAttachment;

@Mapper
public interface GoodsMapper {

	int selectTotalCount();
	
	List<Goods> selectGoodsList(RowBounds rowBounds);
	
	List<GoodsAttachment> selectGoodsAtt();
	
	Goods selectGoodsByNo(Long goodsNo);

	void insertGoods(Goods goods);

}
