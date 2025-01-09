package com.kh.baseball.goods.model.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.kh.baseball.goods.model.vo.Goods;
import com.kh.baseball.goods.model.vo.GoodsAttachment;
import com.kh.baseball.member.model.vo.Member;

public interface GoodsService {
	
	Map<String, Object> selectGoods(int currentPage);
	
	Goods selectGoodsByNo(Long goodsNo);
	
	void insertGoods(Goods goods, MultipartFile upfile, Member loginMember);
	
	Map<String, Object> selectNo(Long goodsNo);
	
	void updateGoods(Goods goods, MultipartFile[] upfile, Member loginMember);
	void updateGoodsAttachment(GoodsAttachment goodsAtt);
	
	void deleteGoods(Goods goods, MultipartFile[] upflie, Member loginMember);
	
	// 구매 시 재고 개수 감소
	void decreaseCount(Goods goods);
	
	// 구매 시 구매 내역에 추가
	void insertHistory(Goods goods, Member loginMember);
	
	// 구매 리뷰
	// List<Review> selectReviewList(Long goodsNo);

}
