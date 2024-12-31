package com.kh.baseball.member.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.baseball.member.model.vo.Member;

@Mapper
public interface MemberMapper {

	Member login(Member member);

	void join(Member member);

	int checkId(String userId);

	int checkNickName(String nickName);

	Member searchId(Member member);

	void updateMember(Member member);

	void deleteMember(Member userInfo); 
	
	
	

}
