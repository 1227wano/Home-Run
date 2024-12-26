package com.kh.baseball.freeboard.controller;

import org.springframework.stereotype.Controller;

import com.kh.baseball.freeboard.model.service.FreeboardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FreeboardController {

	private final FreeboardService freeboardService; 
}
