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
	
	public Map<String, Object> findAll(int currentPage){
		
		// 1절 글 개수 조회
		int totalCount = mapper.findTotalCount();
		
		if(totalCount == 0) { // 예외처리
			log.info("현재 등록된 구장 수 : {}", totalCount);
		}
		
		// 2절 페이징 처리
		PageInfo pi = Pagination.getPageInfo(totalCount, currentPage, 5, 5);
		
		// 3절 DB 조회 결과
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		List<Dom> domList = mapper.findAll(rowBounds);
		List<DomAttachment> attList = mapper.findAttachment();
//		log.info("등록된 구장 정보 : {}", domList);
//		log.info("구장별 이미지 정보 : {}", attList);
		
		// 4절 데이터 가공
		Map<String, Object> map = new HashMap();
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
		
		String changeName = "KH_Hyper_" + currentTime + "_" + randomNum + ext;
		
		// 맨뒤 / 는 upload_files폴더 내부에 만들거다 라는 의미
		String savePath = context.getRealPath("/resources/upload_files/");
		
		try {
			upfile.transferTo(new File(savePath + changeName));
		} catch (IllegalStateException | IOException e) {
			log.info("안올라간다~ {}", upfile);
		}
		// 첨부파일이 존재했다 => 업로드 + Board객체에 originName , changeName set
		domAtt.setOriginName(fileName);
		domAtt.setChangeName(changeName);
		domAtt.setFilePath("/resources/upload_files/");
	}

	@Override
	public void save(Dom dom, MultipartFile upfile) {
		
		mapper.save(dom);
		
		if(!("".equals(upfile.getOriginalFilename()))) {
			
			DomAttachment domAtt = new DomAttachment();
			handleFileUpload(domAtt, upfile);
			
			mapper.saveDomFile(domAtt);
		}
		
	}
	
}
