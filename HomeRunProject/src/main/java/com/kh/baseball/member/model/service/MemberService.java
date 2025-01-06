package com.kh.baseball.member.model.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

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
	Map<String, Object> searchId(Member member);
	
	
	// -- 마이페이지 --
	// 회원 정보 수정
	void updateMember(Member member, HttpSession session);
	
	// 회원 탈퇴
	void deleteMember(String userPwd, HttpSession session);
	
	// 비밀번호 변경
	void changePwd( Map<String, String> map , HttpSession session);

	// 회원가입 - 비밀번호 재비밀번호 확인
	Boolean pwdcheck(String userPwd, String checkPwd);


}
