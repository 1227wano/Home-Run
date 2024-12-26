package com.kh.baseball.player.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baseball.common.ModelAndViewUtil;
import com.kh.baseball.player.model.service.PlayerService;
import com.kh.baseball.player.model.vo.Player;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PlayerController {
	
	private final PlayerService playerService;
	private final ModelAndViewUtil mv;
	
	@PostMapping("insert")
	public ModelAndView savePlayer(Player player, HttpSession session) {
		
		playerService.savePlayer(player);
		
		session.setAttribute("alertMsg", "선수 등록 신청 성공");
		
		return null;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
