package com.kh.baseball.member.model.dao;

import java.util.Map;

import javax.servlet.http.HttpSession;

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

	Member checkToChangePwd(String userId);
	
	int changePwd(Member member);
	
	
	
	

}
