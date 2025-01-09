package com.kh.baseball.goods.model.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartFile;

import com.kh.baseball.common.PageInfo;
import com.kh.baseball.common.Pagination;
import com.kh.baseball.exception.RequestFailedException;
import com.kh.baseball.exception.UserNotFoundException;
import com.kh.baseball.goods.model.dao.GoodsMapper;
import com.kh.baseball.goods.model.vo.Goods;
import com.kh.baseball.goods.model.vo.GoodsAttachment;
import com.kh.baseball.member.model.vo.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableTransactionManagement
public class GoodsServiceImpl implements GoodsService {
	
	private final GoodsMapper goodsMapper;
	private final ServletContext context;
	
	
	private PageInfo getPageInfo(int currentPage) {
		
		int totalCount = goodsMapper.selectTotalCount();
		
		if(totalCount == 0) {
			throw new RequestFailedException("현재 등록된 상품이 존재하지 않습니다");
		}
		
		return Pagination.getPageInfo(totalCount, currentPage, 8, 5);
	}
	
	private List<Goods> getGoodsList(PageInfo pi) {
		
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		
		return goodsMapper.selectGoodsList(rowBounds);
	}
	
	private void handleFileUpload(Goods goods, MultipartFile upfile) {
		
		String fileName = upfile.getOriginalFilename();
		String ext = fileName.substring(fileName.lastIndexOf("."));
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		int randomNum = (int)(Math.random() * 90000) + 10000;
		
		String changeName = "KH_HomeRun_" + currentTime + "_" + randomNum + ext;
		
		String savePath = context.getRealPath("/resources/upload_files/");
		
		try {
			upfile.transferTo(new File(savePath + changeName));
		} catch (IllegalStateException | IOException e) {
			log.info("안올라간다 {}", upfile);
		}
		
		goods.setOriginName(fileName);
		goods.setChangeName("/resources/upload_files/" + changeName);
	}
	
	@Override
	public Goods selectGoodsByNo(Long goodsNo) {
		
		return goodsMapper.selectGoodsByNo(goodsNo);
	}

	@Override
	public Map<String, Object> selectGoods(int currentPage) {
		
		PageInfo pi = getPageInfo(currentPage);
		List<Goods> goodsList = getGoodsList(pi);
		
		Map<String, Object> responseData = new HashMap<>();
		responseData.put("goodsList", goodsList);
		responseData.put("pageInfo", pi);
		
		return responseData;
	}

	@Override
	public void insertGoods(Goods goods, MultipartFile upfile, Member loginMember) {

		if(loginMember == null) {
			throw new UserNotFoundException("로그인 후 이용 가능합니다.");
		}
		
		if(!("".equals(upfile.getOriginalFilename()))) {
			handleFileUpload(goods, upfile);
		}
		
		goodsMapper.insertGoods(goods);
		
	}

	@Override
	public Map<String, Object> selectNo(Long goodsNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateGoods(Goods goods, MultipartFile[] upfile, Member loginMember) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateGoodsAttachment(GoodsAttachment goodsAtt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteGoods(Goods goods, MultipartFile[] upflie, Member loginMember) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decreaseCount(Goods goods) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertHistory(Goods goods, Member loginMember) {
		// TODO Auto-generated method stub
		
	}

}
