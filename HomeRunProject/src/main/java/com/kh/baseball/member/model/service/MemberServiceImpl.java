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
		
		Member userInfo = mapper.login(member); // 사용자가 입력한 id값이 table의 userId에 있으면 예외 발생 -> DB가서 똑같은 값이 있는지 조회
		
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
	public void updateMember(Member member, HttpSession session) {
		
		//log.info("{}", member);
		validator.validateMemberExists(member);
		mapper.updateMember(member);
		session.setAttribute("loginUser", mapper.login(member)); //여기서 넘기는것으로 수정
	}
	

	@Override
	public void deleteMember(String userPwd, HttpSession session) {
		
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		loginUser.setUserPwd(userPwd);
		
		Member userInfo = validator.validateMemberExists(loginUser);
		
		/*
		if(!!! passwordEncoder.matches(loginUser.getUserPwd(), userInfo.getUserPwd())) {
			throw new ComparePasswordException("비밀번호가 일치하지 않습니다.");
		}
		*/
		
		mapper.deleteMember(userInfo);
		
	}

	
	@Override
	public void changePwd(Map<String, String> map, HttpSession session) {
		
		// 새로운 값이 넘어옴
		// 존재하는 회원인지 확인
		 Member member = validator.validateMemberExists(map.get("userId"));
		
		// 비밀번호 확인
		if(!!!passwordEncoder.matches(map.get("userPwd"), member.getUserPwd())) {
			throw new ComparePasswordException("비밀번호가 일치하지 않습니다.");
		}
		
		// 새로운 비밀번호 암호화
		String userPwd = passwordEncoder.encode(map.get("changePwd"));
		
		member.setUserPwd(userPwd);
		
		int result = mapper.changePwd(member);
		
		if(result > 0) {
			session.setAttribute("loginUser", result);
		}
		
	}

	@Override
	public Boolean pwdcheck(String userPwd, String checkPwd) {
		
		return userPwd.equals(checkPwd);
		
	}
	
	
	
	
	

	
	
	

}
