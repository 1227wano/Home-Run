package com.kh.baseball.member.model.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
		return mapper.checkId(userId) > 0 ? "IIIII" : "IIIIY";
	}

	@Override
	public String checkNickName(String nickName) {
		return mapper.checkNickName(nickName) > 0 ? "NNNNN" : "NNNNY";
	}

	@Override
	public Map<String, Object> searchId(Member member) {
		
		Member searchId = validator.validateIdExists(member);
		Map<String, Object> map = new HashMap();
		map.put("successId", searchId);
		
		return map;
		
	
	}

	@Override
	public String findByPassword(Member member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateMember(Member member, HttpSession session) {
		
		//log.info("{}", member);
		validator.validateMemberExists(member);
		mapper.updateMember(member);
		session.setAttribute("loginUser", mapper.login(member)); //여기서 넘기는것으로 수정
	}

	@Override
	public void deleteMember(String userPwd, HttpSession session) {
		// TODO Auto-generated method stub
		
	}
	
	

}
