package com.kh.baseball.dom.model.service;

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
import com.kh.baseball.dom.model.dao.DomMapper;
import com.kh.baseball.dom.model.vo.Dom;
import com.kh.baseball.dom.model.vo.DomAttachment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableTransactionManagement
public class DomServiceImpl implements DomService {
	
	private final DomMapper mapper;
	private final ServletContext context;
	
	public Map<String, Object> selectDomList(int currentPage){
		
		int totalCount = mapper.selectTotalCount();
		
		if(totalCount == 0) {
			log.info("현재 등록된 구장 수 : {}", totalCount); // 예외처리
		}
		
		PageInfo pi = Pagination.getPageInfo(totalCount, currentPage, 5, 5);
		
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		List<Dom> domList = mapper.selectDomList(rowBounds);
		List<DomAttachment> attList = mapper.selectAttachmentList();
		
		Map<String, Object> map = new HashMap<>();
		map.put("domList", domList);
		map.put("pageInfo", pi);
		map.put("attList", attList);
		
		return map;
	}
	
	// 사용자가 첨부한 첨부파일이름의 중복이 발생할 수 있기 때문에 DB에 저장 할 때 우리만의 저장 방식으로 저장
	private void handleFileUpload(DomAttachment domAtt, MultipartFile upfile) {
		
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
		domAtt.setOriginName(fileName);
		domAtt.setChangeName(changeName);
		domAtt.setFilePath("/resources/upload_files/");
	}

	@Override
	public void insertDom(Dom dom, MultipartFile upfile) {
		
		if(dom.getDomName().length() > 100 || dom.getDomContent().length() > 1000
		   || dom.getDomAddr().length() > 100) {
			log.info("제한 글자수 보다 큰 값을 입력하여 추가할 수 없음"); // 예외처리
		}
		
		int resultDom = mapper.insertDom(dom);
		int resultAtt = 1;
		
		if(!("".equals(upfile.getOriginalFilename()))) {
			
			DomAttachment domAtt = new DomAttachment();
			handleFileUpload(domAtt, upfile);
			
			resultAtt = mapper.insertDomFile(domAtt);
		}
		
		if((resultDom * resultAtt) < 1) {
			log.info("등록 실패"); // 예외처리
		}
		
	}

	@Override
	public Map<String, Object> selectId(Long id) {
		
		Map<String, Object> map = new HashMap<>();
		Dom dom = mapper.selectId(id);
		map.put("dom", dom);
		
		return map;
	}

	@Override
	public void updateDom(Dom dom, MultipartFile upfile) {
		
		if(dom.getDomNo() == null || dom.getDomNo() <= 0) {
			log.info("유효하지 않은 구장 식별 번호"); // 예외처리
		}
		
		Dom selectedDom = mapper.selectId(dom.getDomNo());
		if(selectedDom == null) {
			log.info("찾을 수 없는 구장"); // 예외처리
		}
		
		if(dom.getDomName().length() > 100 || dom.getDomContent().length() > 3000 
		   || dom.getDomAddr().length() > 100) {
			log.info("제한 글자수 보다 큰 값을 입력하여 추가할 수 없음"); // 예외처리
		}
		
		int resultAtt = 1;
		DomAttachment domAtt = null;
		
		if(!("".equals(upfile.getOriginalFilename()))) {
			
			if(selectedDom.getImagePath() != null) {
				
				String imagePath = selectedDom.getImagePath();
				
				imagePath = imagePath.substring(imagePath.indexOf("/"));
				
				new File(context.getRealPath(imagePath)).delete();
//				log.info("구장 파일 경로 : {}", selectedDom.getImagePath());
			}
			domAtt = new DomAttachment();
			domAtt.setRefDno(selectedDom.getDomNo());
			handleFileUpload(domAtt, upfile);
			
			resultAtt = mapper.updateDomFile(domAtt);
		}
		
		int resultDom = mapper.updateDom(dom);
		
		if((resultDom * resultAtt) < 1) {
			log.info("업데이트 실패"); // 예외처리
		}
	}

	
	
	
}
