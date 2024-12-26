package com.kh.baseball.dom.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	public void save(Dom dom, MultipartFile upfile) {
		
		mapper.save(dom);
		
		if(upfile != null) {
			mapper.saveDomFile(upfile);
		}
		
	}
	
}
