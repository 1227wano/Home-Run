package com.kh.baseball.member.model.service;

import com.kh.baseball.member.model.vo.Member;

public interface MemberService {
	
	//회원가입
	void join(Member member);
	
	//로그인
	Member login(Member member);
	
	//아이디 중복 조회
	String checkId(String userId);
	
	//닉네임 중복 조회
	String checkNickName(String nickName);
	
	//아이디 찾기
	String findById(Member member);
	
	//비밀번호 찾기 
	String findByPassword(Member member);

}
