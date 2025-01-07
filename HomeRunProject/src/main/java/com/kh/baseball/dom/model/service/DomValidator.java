package com.kh.baseball.dom.model.service;

import org.springframework.stereotype.Component;

import com.kh.baseball.dom.model.dao.DomMapper;
import com.kh.baseball.dom.model.vo.Dom;
import com.kh.baseball.exception.IdNotFoundException;
import com.kh.baseball.exception.InvalidParameterException;
import com.kh.baseball.exception.TooLargeValueException;
import com.kh.baseball.member.model.dao.MemberMapper;
import com.kh.baseball.member.model.service.MemberValidator;
import com.kh.baseball.member.model.vo.Member;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DomValidator {
	
	private final DomMapper domMapper;
	private final MemberMapper memberMapper;
	private final MemberValidator memberValidator;
	
	// domNo 유효 검증
	public void validateDomNo(Long domNo) {
		
		Dom checkDom = domMapper.selectId(domNo);
		
		if(checkDom == null || checkDom.getDomNo() <= 0) {
			throw new IdNotFoundException("유효하지 않은 구장 번호입니다.");
		}
	}
	
	// dom테이블 데이터 추가/변경 시 값의 길이 검증
	public void validateParameterLength(Dom dom) {
		if(dom.getDomName().length() > 100 || dom.getDomContent().length() > 3000
		   || dom.getDomAddr().length() > 100) {
			throw new TooLargeValueException("글자 제한 수 초과");
		}
	}

	// 관리자 계정 유효성 검사
	public void validateAuthority(Member member) {
		
		memberValidator.validateMemberExists(member);
		
		if("admin" != (memberMapper.searchId(member)).getUserId()) {
			throw new IdNotFoundException("유효하지 않은 아이디로 요청을 보냈습니다.");
		}
	}
	
	// dom 필수 입력값 검증
	public void validateDom(Dom dom) {
		
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
	
	public void reconversion(Dom dom) {
		
		String domName = reEscapeHtml(dom.getDomName());
		String domContent = reEscapeHtml(dom.getDomContent());
		String domAddr = reEscapeHtml(dom.getDomAddr());
		
		dom.setDomName(convertBrToNewline(domName));
		dom.setDomContent(convertBrToNewline(domContent));
		dom.setDomAddr(convertBrToNewline(domAddr));
	}
	
	public String reEscapeHtml(String value) {
		return value.replaceAll("&lt;", "<") // 메소드 체이닝 가능
					.replaceAll("&gt;", ">");
	}
	
	public String convertBrToNewline(String value) {
		return value.replaceAll("<br>", "\n");
	}
	
	public String escapeHtml(String value) {
		return value.replaceAll("<", "&lt;") // 메소드 체이닝 가능
					.replaceAll(">", "&gt;");
	}
	
	public String convertNewlineToBr(String value) {
		return value.replaceAll("\n", "<br>");
	}
	
	public void validateDom(Dom dom, Member loginMember) {
		validateAuthority(loginMember);
		validateDomNo(dom.getDomNo());
		validateParameterLength(dom);
		validateDom(dom);
	}
	
	
}
