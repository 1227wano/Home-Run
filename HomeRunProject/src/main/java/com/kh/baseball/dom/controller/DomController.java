package com.kh.baseball.dom.controller;

import org.springframework.stereotype.Controller;

import com.kh.baseball.dom.model.service.DomService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DomController {

	private final DomService domService;
	
}
