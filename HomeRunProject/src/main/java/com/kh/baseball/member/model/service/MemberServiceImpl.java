package com.kh.baseball.member.model.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.kh.baseball.exception.ComparePasswordException;
import com.kh.baseball.member.model.dao.MemberMapper;
import com.kh.baseball.member.model.vo.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@EnableTransactionManagement 
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper mapper;
	private final MemberValidator validator;
	private final PasswordEncryptor passwordEncoder;

	@Override
	public void join(Member member) {
		
		Member userInfo = mapper.login(member);
		
		validator.validateJoinMember(member);
		
		member.setUserPwd(passwordEncoder.encode(member.getUserPwd()));
		
		mapper.join(member);
		

	}

	@Override
	public Member login(Member member) {

		Member loginMember = validator.validateMemberExists(member);
		
		if(!!!passwordEncoder.matches(member.getUserPwd(), loginMember.getUserPwd())) {
			throw new ComparePasswordException("비밀번호가 일치하지 않습니다.");
		} else {
			return loginMember;
		}
	}
	
	

	@Override
	public String checkId(String userId) {
		return mapper.checkId(userId) >0 ? "NNNNN" : "NNNNY";
	}

	@Override
	public String checkNickName(String nickName) {
		return mapper.checkNickName(nickName) > 0 ? "NNNNN" : "NNNNY";
	}

	@Override
	public String findById(Member member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findByPassword(Member member) {
		// TODO Auto-generated method stub
		return null;
	}

}
