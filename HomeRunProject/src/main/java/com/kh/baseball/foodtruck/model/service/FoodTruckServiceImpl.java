package com.kh.baseball.foodtruck.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.baseball.common.PageInfo;
import com.kh.baseball.foodtruck.model.dao.FoodTruckMapper;
import com.kh.baseball.foodtruck.model.vo.FoodTruck;
import com.kh.baseball.foodtruck.model.vo.FoodTruckFile;
import com.kh.baseball.member.model.vo.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@RequiredArgsConstructor

public class FoodTruckServiceImpl implements FoodTruckService {

	private final FoodTruckMapper mapper;
	private final FoodTruckValidator validator;

	@Override
	public Map<String, Object> selectFoodTruckList(int currentPage) {
		
		int totalCount = validator.getTotalCount();
		
		//log.info("게시글 개수 : {}" , totalCount);
		
		PageInfo pi = validator.getPageInfo(totalCount, currentPage);
		
		List<FoodTruck> foodTruck = validator.getFoodTruckList(pi);
		
		List<FoodTruck> dom = mapper.selectDom();
		
		List<FoodTruckFile> foodTruckFile = mapper.selectFoodTruckFile();
		
		log.info("첨부파일 : {}", foodTruckFile);
		
		
		//log.info("{}",dom);
		
		Map<String, Object> map = new HashMap();
		map.put("dom", dom);
		map.put("foodTruck", foodTruck);
		map.put("pageInfo", pi);
		map.put("foodTruckFile", foodTruckFile);
		
		return map;
	}
	
	

	@Transactional
	@Override
	public void insertFoodTruck(FoodTruck foodTruck, MultipartFile[] upfile) {
		
		validator.validateFoodTruck(foodTruck);
		
		mapper.insertFoodTruck(foodTruck);
		
		//log.info("{}" , foodTruck);
		log.info("{}" , upfile);
		
		for(int i = 0; i < 3; i++) {
			if(!!!("".equals(upfile[i].getOriginalFilename()))) {
				int fileType = i + 1;
				FoodTruckFile foodTruckFile = validator.handelFileUpload(upfile[i], fileType);
				mapper.insertFoodTruckFile(foodTruckFile);
			}
		}
		
	}
	


	@Override
	public FoodTruck selectById(int foodTruckNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectDomName() {
		
		List<FoodTruck> dom = mapper.selectDom();
		
		//log.info("{}" , dom);
		
		Map<String, Object> map = new HashMap();
		map.put("dom", dom);
		
		return map;
	}

	@Override
	public String foodTruckNameCheck(String foodTruckName) {
		
		//log.info("{}" , foodTruckName);
		
		return mapper.foodTruckNameCheck(foodTruckName) > 0 ? "FFFFF" : "FFFFT";
	}



	@Override
	public Map<String, Object> foodTruckSelectDom(int domNo) {
		
		List<FoodTruck> foodTruck = mapper.foodTruckSelectDom(domNo);
		List<FoodTruckFile> foodTruckFile = mapper.selectFoodTruckFile();
		List<FoodTruck> dom = mapper.selectDom();
		
		Map<String, Object> map = new HashMap();
		
		map.put("foodTruck", foodTruck);
		map.put("foodTruckFile", foodTruckFile);
		map.put("dom", dom);
		
		return map;
	}

}
