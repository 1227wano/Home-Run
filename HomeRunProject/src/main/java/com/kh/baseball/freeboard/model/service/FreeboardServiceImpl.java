package com.kh.baseball.freeboard.model.service;

import org.springframework.stereotype.Service;

import com.kh.baseball.freeboard.model.dao.FreeboardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FreeboardServiceImpl implements FreeboardService {

	public final FreeboardMapper freeboardMapper;
}
