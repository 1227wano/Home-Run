package com.kh.baseball.dom.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.kh.baseball.dom.model.dao.DomMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableTransactionManagement
public class DomServiceImpl implements DomService {
	
	private final DomMapper mapper; 
	
}
