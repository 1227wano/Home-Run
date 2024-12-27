package com.kh.baseball.dom.model.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.kh.baseball.dom.model.vo.Dom;
import com.kh.baseball.dom.model.vo.DomAttachment;

public interface DomService {

	Map<String, Object> findAll(int currentPage);

	void save(Dom dom, MultipartFile upfile);

	Map<String, Object> findById(Long id);
	
	
}
