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
import com.kh.baseball.exception.RequestFailedException;
import com.kh.baseball.member.model.vo.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableTransactionManagement
public class DomServiceImpl implements DomService {
	
	private final DomMapper domMapper;
	private final DomValidator domValidator;
	private final ServletContext context;
	
	@Override
	public Map<String, Object> selectDomList(int currentPage){
		
		PageInfo pi = getPageInfo(currentPage);
		List<Dom> domList = getDomList(pi);
		List<DomAttachment> attList = domMapper.selectAttachmentList();
		
		Map<String, Object> map = new HashMap<>();
		map.put("domList", domList);
		map.put("pageInfo", pi);
		map.put("attList", attList);
		
		return map;
	}
	
	private PageInfo getPageInfo(int currentPage) {
		
		int totalCount = domMapper.selectTotalCount();
		
		if(totalCount == 0) {
			throw new RequestFailedException("현재 등록된 구장이 존재하지 않습니다");
		}
		
		return Pagination.getPageInfo(totalCount, currentPage, 5, 5);
	}
	
	private List<Dom> getDomList(PageInfo pi){
		
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		
		return domMapper.selectDomList(rowBounds);
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
	public void insertDom(Dom dom, MultipartFile upfile, Member loginMember) {
		
		domValidator.validateDom(dom, loginMember);
		
		int resultDom = domMapper.insertDom(dom);
		int resultAtt = 1;
		
		if(!("".equals(upfile.getOriginalFilename()))) {
			
			DomAttachment domAtt = new DomAttachment();
			handleFileUpload(domAtt, upfile);
			
			resultAtt = domMapper.insertDomFile(domAtt);
		}
		
		if((resultDom * resultAtt) < 1) {
			throw new RequestFailedException("요청 처리에 실패했습니다.");
		}
	}

	@Override
	public Map<String, Object> selectId(Long id) {
		
		domValidator.validateDomNo(id);
		
		Map<String, Object> map = new HashMap<>();
		Dom dom = domMapper.selectId(id);

		domValidator.reconversion(dom);
		
		map.put("dom", dom);
		
		return map;
	}

	@Override
	public void updateDom(Dom dom, MultipartFile upfile, Member loginMember) {
		
		domValidator.validateDom(dom, loginMember);
		
		int resultAtt = 1;
		DomAttachment domAtt = null;
		
		if(!("".equals(upfile.getOriginalFilename()))) {
			
			if(dom.getImagePath() != null) {
				String imagePath = dom.getImagePath();
				imagePath = imagePath.substring(imagePath.indexOf("/"));
				new File(context.getRealPath(imagePath)).delete();
			}
			
			domAtt = new DomAttachment();
			domAtt.setRefDno(dom.getDomNo());
			handleFileUpload(domAtt, upfile);
			
			resultAtt = domMapper.updateDomFile(domAtt);
		}
		
		int resultDom = domMapper.updateDom(dom);
		
		if((resultDom * resultAtt) < 1) {
			throw new RequestFailedException("정보 수정에 실패했습니다.");
		}
	}

	@Override
	public void deleteDom(Dom dom, MultipartFile upfile, Member loginMember) {
		
		domValidator.validateAuthority(loginMember);
		domValidator.validateDomNo(dom.getDomNo());
		
		int domResult = domMapper.deleteDom(dom);
		int domAttResult = 1;
		
		if(dom.getImagePath() != null) {
			domAttResult = domMapper.deleteDomFile(dom.getDomNo());
		}
		
		if((domResult * domAttResult) < 1) {
			throw new RequestFailedException("요청 처리에 실패했습니다.");
		}
	}

	
	
}
