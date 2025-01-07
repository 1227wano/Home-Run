package com.kh.baseball.foodtruck.model.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.kh.baseball.common.PageInfo;
import com.kh.baseball.common.Pagination;
import com.kh.baseball.exception.FailToFileUploadException;
import com.kh.baseball.exception.FoodTruckNoValueException;
import com.kh.baseball.exception.FoodTruckNotFoundException;
import com.kh.baseball.foodtruck.model.dao.FoodTruckMapper;
import com.kh.baseball.foodtruck.model.vo.FoodTruck;
import com.kh.baseball.foodtruck.model.vo.FoodTruckFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class FoodTruckValidator {
	
	private final FoodTruckMapper  mapper;
	private final ServletContext context;
	
	public int getTotalCount() {
		
		int totalCount = mapper.selectTotalCount();
		// log.info("{}",totalCount);
		if(totalCount == 0) {
			throw new FoodTruckNotFoundException("푸드트럭 게시글이 없습니다.");
		}
		return totalCount;
	}
	
	
	public PageInfo getPageInfo(int totalCount, int currentPage) {
		return Pagination.getPageInfo(totalCount, currentPage, 6, 6);
	}
	
	
	public List<FoodTruck> getFoodTruckList(PageInfo pi){
		
		int offset = ((pi.getCurrentPage() - 1) * (pi.getBoardLimit()));
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		
		return mapper.selectFoodTruckList(rowBounds);
	}
	
	public RowBounds getRowBounds(PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return rowBounds;
	}
	
	public void validateFoodTruck(FoodTruck foodTruck) {
		if(foodTruck == null || 
			foodTruck.getFoodTruckName()== null || foodTruck.getFoodTruckName().trim().isEmpty()||
			foodTruck.getFoodTruckDay() == null || foodTruck.getFoodTruckDay().trim().isEmpty()||
			foodTruck.getFoodTruckContent() == null || foodTruck.getFoodTruckContent().trim().isEmpty()){
				throw new FoodTruckNoValueException("푸드트럭 게시물에 부적절한 입력입니다.");
			}
		
		String foodTruckName = escapeHtml(foodTruck.getFoodTruckName());
		String foodTruckDay = escapeHtml(foodTruck.getFoodTruckDay());
		String foodTruckContent = escapeHtml(foodTruck.getFoodTruckContent());
		
		foodTruck.setFoodTruckName(convertNewlineToBr(foodTruckName));
		foodTruck.setFoodTruckDay(convertNewlineToBr(foodTruckDay));
		foodTruck.setFoodTruckContent(convertNewlineToBr(foodTruckContent));

	}
	
	public String escapeHtml(String value) {
		return value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
	
	public String convertNewlineToBr(String value) {
		return value.replaceAll("\n", "<br>");
	}
	
	
	public FoodTruckFile handelFileUpload(MultipartFile upfile, int fileType) {
		
		String fileName = upfile.getOriginalFilename();
		String ext = fileName.substring(fileName.lastIndexOf("."));
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		int randomNum = (int)Math.random()*90000 + 10000;
		String changeName = currentTime + randomNum + ext;
		
		String savePath = context.getRealPath("/resource/upload_files/");
		
		FoodTruckFile foodTruckFile = FoodTruckFile.builder().originName(fileName)
															 .changeName(changeName)
															 .fileType(fileType)
															 .build();
		
		try {
			upfile.transferTo(new File(savePath + changeName));
		}catch(IllegalStateException | IOException e) {
			throw new FailToFileUploadException("파일이 문제가 발생했습니다.");
		}
		
		return foodTruckFile;
		
	}
	
	
	
	
}
