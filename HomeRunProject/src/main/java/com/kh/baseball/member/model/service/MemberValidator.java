package com.kh.baseball.member.model.service;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.kh.baseball.exception.IdNotFoundException;
import com.kh.baseball.exception.TooLargeValueException;
import com.kh.baseball.exception.UserFoundException;
import com.kh.baseball.exception.UserIdNotFoundException;
import com.kh.baseball.member.model.dao.MemberMapper;
import com.kh.baseball.member.model.vo.Member;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberValidator {
	
	private final MemberMapper mapper ;
	
	// Login 
	public Member validateMemberExists(Member member) {
		Member existingMember = mapper.login(member);
		
		if(existingMember != null) {
			return existingMember;
		}
		throw new UserIdNotFoundException("존재하지 않는 사용자입니다.");
	}
	
	public void validateDuplicateMember(Member member) {
		Member existingMember = mapper.login(member);
		if(existingMember != null && !member.getUserId().equals(existingMember.getUserId())) {
			throw new UserFoundException("이미 존재하는 회원입니다.");
		}
	}
	
	public void validateIdLength(Member member) {
		if(member.getUserId().length() > 30) {
			throw new TooLargeValueException("8글자 이하로 작성해주세요.");
		}
	}
	
	public void validateJoinMember(Member member) {
		validateDuplicateMember(member);
		validateIdLength(member);
	}
	
	// ID변경에서 사용
	public Member validateIdExists(Member member) {
		
		System.out.println(member);
		Member existingId = mapper.searchId(member);
		if(existingId != null) {
			return existingId;
		}
		throw new IdNotFoundException("아이디가 존재하지 않습니다.");
	}

	
	
	public Member validateMemberExists(String userId) {
		
		Member existingMember = mapper.checkToChangePwd(userId);
		
		if(existingMember != null) {
			return existingMember;
		}
		throw new UserIdNotFoundException("존재하지 않는 사용자입니다.");
	}
	
	

	

	

	

}
