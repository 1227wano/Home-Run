package com.kh.baseball.player.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baseball.common.ModelAndViewUtil;
import com.kh.baseball.common.model.vo.ResponseData;
import com.kh.baseball.player.model.service.PlayerService;
import com.kh.baseball.player.model.vo.Player;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PlayerController {
	
	private final PlayerService playerService;
	private final ModelAndViewUtil mv;
	
	// 선수 등록 양식 열어
	@GetMapping("savePlayerform.player")
	public String playerform() {
		return "player/player_enroll";	 
	}
	
	// 선수 등록
	@PostMapping("savePlayer.player")
	public ModelAndView savePlayer(Player player, MultipartFile upfile, HttpSession session) {
		
		playerService.savePlayer(player, upfile);
		
		session.setAttribute("alertMsg", "선수 등록 신청 성공");
		
		log.info("입력된거 : {}", player);
		
		// 응답페이지 미정
		return mv.setViewNameAndData("redirect:/", null);
		
	}
	
	/* 선수 조회 페이지 열어 <- 이거 왜만듦?
	@GetMapping("findPlayerform.player")
	public String enrollFrom(Player player) {
		return "player/playerList";
	}
	*/
	
	// 등록된 전체 선수 열람
	@GetMapping("findAllPlayer.player")
	public ModelAndView findAllPlayer(Player player, @RequestParam(value="page", defaultValue="1") int page) {
		
		Map<String, Object> map = playerService.findAllPlayerKorean(page);
		
		return mv.setViewNameAndData("player/playerList", map); 
	}
	
	
	@GetMapping("playerList.player")
	public ModelAndView forwardPlayerList() {
		return mv.setViewNameAndData("redirect:/", null);
	}
	
	
	@GetMapping("findMorePlayer.player")
	public ResponseEntity<ResponseData> ajaxFindMorePlayer(int moreNum){
		List<Player> players = playerService.findMorePlayer(moreNum);
		ResponseData response = ResponseData.builder()
											.status(HttpStatus.OK.toString())
											.data(players)
											.build();
				
		return new ResponseEntity<ResponseData>(response, HttpStatus.OK);
	}
	
	
	

}
