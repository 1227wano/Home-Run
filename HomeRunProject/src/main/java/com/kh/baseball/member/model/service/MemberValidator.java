package com.kh.baseball.member.model.service;

import org.springframework.stereotype.Component;

import com.kh.baseball.exception.UserIdNotFoundException;
import com.kh.baseball.member.model.dao.MemberMapper;
import com.kh.baseball.member.model.vo.Member;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberValidator {
	
	private final MemberMapper mapper ;
	
	public Member validateMemberExists(Member member) {
		Member existingMember = mapper.login(member);
		
		if(existingMember != null) {
			return existingMember;
		}
		throw new UserIdNotFoundException("존재하지 않는 아이디입니다.");
	}
	
	

}
