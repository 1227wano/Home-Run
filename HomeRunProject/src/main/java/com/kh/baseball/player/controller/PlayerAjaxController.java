package com.kh.baseball.player.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.baseball.common.ModelAndViewUtil;
import com.kh.baseball.common.model.vo.ResponseData;
import com.kh.baseball.player.model.service.PlayerService;
import com.kh.baseball.player.model.vo.Player;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("player")
@RequiredArgsConstructor
public class PlayerAjaxController {
	
	private final PlayerService playerService;
	private final ModelAndViewUtil mv;
	
	// 선수 더보기
	@GetMapping(produces="application/json; charset=UTF-8")
	public ResponseEntity<ResponseData> findMorePlayer(int page) {
		//log.info("{}", page);
		List<Player> players = playerService.findMorePlayer(page);
		//log.info("{}", players.get(0));
		ResponseData response = ResponseData.builder()
											.status(HttpStatus.OK.toString())
											.data(players)
											.build();
				
		return new ResponseEntity<ResponseData>(response, HttpStatus.OK);
	}

		

}
