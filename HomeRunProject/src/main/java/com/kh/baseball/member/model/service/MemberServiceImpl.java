package com.kh.baseball.member.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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

	@Override
	public void join(Member member) {

	}

	@Override
	public Member login(Member member) {

		Member loginMember = validator.validateMemberExists(member);
		
		
		return null;
	}

	@Override
	public String checkId(String userId) {
		// TODO Auto-gene rated method stub
		return null;
	}

	@Override
	public String checkNickName(String nickName) {
		// TODO Auto-generated method stub
		return null;
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
