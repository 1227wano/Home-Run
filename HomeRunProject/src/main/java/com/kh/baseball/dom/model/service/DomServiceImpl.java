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
import com.kh.baseball.exception.IdNotFoundException;
import com.kh.baseball.exception.InvalidParameterException;
import com.kh.baseball.exception.RequestFailedException;
import com.kh.baseball.exception.TooLargeValueException;
import com.kh.baseball.member.model.dao.MemberMapper;
import com.kh.baseball.member.model.service.MemberValidator;
import com.kh.baseball.member.model.vo.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableTransactionManagement
public class DomServiceImpl implements DomService {
	
	private final MemberMapper memberMapper;
	private final MemberValidator memberValidator;
	
	private final DomMapper domMapper;
	private final ServletContext context;
	
	public Map<String, Object> selectDomList(int currentPage){
		
		int totalCount = domMapper.selectTotalCount();
		
		if(totalCount == 0) {
			log.info("현재 등록된 구장 수 : {}", totalCount); // 예외처리
		}
		
		PageInfo pi = Pagination.getPageInfo(totalCount, currentPage, 5, 5);
		
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		List<Dom> domList = domMapper.selectDomList(rowBounds);
		List<DomAttachment> attList = domMapper.selectAttachmentList();
		
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

	private void validateAuthority(Member member) {
		if("admin" != (memberMapper.searchId(member)).getUserId()) {
			log.info("요청 보낸 사용자 아이디 : {}", (memberMapper.searchId(member)).getUserId());
			throw new IdNotFoundException("유효하지 않은 아이디로 요청을 보냈습니다.");
		}
	}
	
	private void validateParameterLength(Dom dom) {
		if(dom.getDomName().length() > 100 || dom.getDomContent().length() > 3000
		   || dom.getDomAddr().length() > 100) {
			throw new TooLargeValueException("글자 제한 수 초과");
		}
	}
	
	private void validateDom(Dom dom) {
		
		if(dom == null ||
		   dom.getDomName() == null || dom.getDomName().trim().isEmpty() || 
		   dom.getDomContent() == null || dom.getDomContent().trim().isEmpty() ||
		   dom.getDomAddr() == null || dom.getDomAddr().trim().isEmpty()) {
			throw new InvalidParameterException("필수 입력 값이 부적절합니다.");
		}
		
		String domName = escapeHtml(dom.getDomName());
		String domContent = escapeHtml(dom.getDomContent());
		String domAddr = escapeHtml(dom.getDomAddr());
		
		dom.setDomName(convertNewlineToBr(domName));
		dom.setDomContent(convertNewlineToBr(domContent));
		dom.setDomAddr(convertNewlineToBr(domAddr));
	}
	
	private String reEscapeHtml(String value) {
		return value.replaceAll("&lt;", "<") // 메소드 체이닝 가능
					.replaceAll("&gt;", ">");
	}
	
	private String convertBrToNewline(String value) {
		return value.replaceAll("<br>", "\n");
	}
	
	private String escapeHtml(String value) {
		return value.replaceAll("<", "&lt;") // 메소드 체이닝 가능
					.replaceAll(">", "&gt;");
	}
	
	private String convertNewlineToBr(String value) {
		return value.replaceAll("\n", "<br>");
	}
	
	private void validateDomNo(Dom dom) {
		
		Dom checkDom = domMapper.selectId(dom.getDomNo());
		
		if(checkDom == null || checkDom.getDomNo() <= 0) {
			throw new IdNotFoundException("유효하지 않은 구장 번호입니다.");
		}
	}
	
	
	@Override
	public void insertDom(Dom dom, MultipartFile upfile, Member loginMember) {
		
		memberValidator.validateMemberExists(loginMember);
		
		validateAuthority(loginMember);
		validateDom(dom);
		validateParameterLength(dom);
		
		int resultDom = domMapper.insertDom(dom);
		int resultAtt = 1;
		
		if(!("".equals(upfile.getOriginalFilename()))) {
			
			DomAttachment domAtt = new DomAttachment();
			handleFileUpload(domAtt, upfile);
			
			resultAtt = domMapper.insertDomFile(domAtt);
		}
		
		if((resultDom * resultAtt) < 1) {
			log.info("등록 실패"); // 예외처리
		}
		
	}

	private void reconversion(Dom dom) {
		
		String domName = reEscapeHtml(dom.getDomName());
		String domContent = reEscapeHtml(dom.getDomContent());
		String domAddr = reEscapeHtml(dom.getDomAddr());
		
		dom.setDomName(convertBrToNewline(domName));
		dom.setDomContent(convertBrToNewline(domContent));
		dom.setDomAddr(convertBrToNewline(domAddr));
		
	}
	
	@Override
	public Map<String, Object> selectId(Long id) {
		
		Map<String, Object> map = new HashMap<>();
		Dom dom = domMapper.selectId(id);

		reconversion(dom);
		
		map.put("dom", dom);
		
		return map;
	}

	@Override
	public void updateDom(Dom dom, MultipartFile upfile, Member loginMember) {
		
		memberValidator.validateMemberExists(loginMember);

		validateDomNo(dom);
		validateParameterLength(dom);
		validateDom(dom);
		
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
			log.info("업데이트 실패"); // 예외처리
		}
	}

	@Override
	public void deleteDom(Dom dom, MultipartFile upfile, Member loginMember) {
		
		memberValidator.validateMemberExists(loginMember);
		validateDomNo(dom);
		
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
