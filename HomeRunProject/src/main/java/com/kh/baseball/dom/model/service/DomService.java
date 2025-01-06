package com.kh.baseball.dom.model.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.kh.baseball.dom.model.vo.Dom;
import com.kh.baseball.member.model.vo.Member;

public interface DomService {

	Map<String, Object> selectDomList(int currentPage);

	void insertDom(Dom dom, MultipartFile upfile, Member loginMember);

	Map<String, Object> selectId(Long id);

	void updateDom(Dom dom, MultipartFile upfile, Member loginMember);
	
	void deleteDom(Dom dom, MultipartFile upfile, Member loginMember);
}
