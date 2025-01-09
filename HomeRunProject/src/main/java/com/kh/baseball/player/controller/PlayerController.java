package com.kh.baseball.player.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baseball.common.ModelAndViewUtil;
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
	@GetMapping("savePlayerform")
	public String playerform() {
		return "player/player_enroll";	 
	}
	
	// 선수 등록
	@PostMapping("savePlayer")
	public ModelAndView savePlayer(Player player, MultipartFile upfile, HttpSession session) {
		playerService.savePlayer(player, upfile);
		session.setAttribute("alertMsg", "선수 등록이 성공적으로 신청되었습니다. 승인을 기다려주세요.");	
		return mv.setViewNameAndData("redirect:/", null);
	}
	
	// 등록된 전체 선수 열람
	@GetMapping("findAllPlayer")
	public ModelAndView findAllPlayer(Player player, @RequestParam(value="page", defaultValue="1") int page) {
		Map<String, Object> map = playerService.findAllPlayerKorean(page);
		return mv.setViewNameAndData("player/playerList", map); 
	}
	
	// 선수 상세보기
	@GetMapping("findPlayer/{playerNo}")
	public ModelAndView forwardPlayerList(@PathVariable(name="playerNo") int playerNo) {
		Map<String, Object> responseData = playerService.selectPlayer(playerNo);
		return mv.setViewNameAndData("player/playerDetail", responseData);
	}
	
	// 마이페이지에서 선수및팀 관련 정보로 넘기는데 선수 상세정보 같이 넘기기
	@GetMapping("playerAndTeam/{userNo}")
	public ModelAndView playerAndTeam(@PathVariable(name="userNo") int userNo) {
		
		Map<String, Object> responseData = playerService.mypagePlayer(userNo);
		
		return mv.setViewNameAndData("player/playerAndTeam", responseData);
	}
	
	// 선수 수정 양식 열어
	@GetMapping("updatePlayerform")
	public ModelAndView updateform(int playerNo) {
		Map<String, Object> responseData = playerService.selectPlayer(playerNo);
		return mv.setViewNameAndData("player/player_update", responseData); 
	}
	
	// 선수 수정
	@PostMapping("updatePlayer")
	public ModelAndView updatePlayer(Player player, MultipartFile upfile, HttpSession session) {
		// log.info("{}", player);
		playerService.updatePlayer(player, upfile);
		
		session.setAttribute("alertMsg", "선수 정보가 수정되었습니다.");
		
		return mv.setViewNameAndData("redirect:/playerAndTeam/" + player.getUserNo(), null); // 되나?
		
	}
	
	// 선수 삭제
	@PostMapping("deletePlayer")
	public ModelAndView deletePlayer(int playerNo, HttpSession session) {
		playerService.deletePlayer(playerNo);
		
		session.setAttribute("alertMsg", "선수 정보가 삭제되었습니다.");
		
		return mv.setViewNameAndData("redirect:/playerAndTeam/" + playerNo, null);
	
	}
	
	// 선수 소속신청 양식 열어
	@GetMapping("joinTeamform")
	public ModelAndView joinTeamform(int playerNo) {
		Map<String, Object> responseData = playerService.selectPlayer(playerNo);
		return mv.setViewNameAndData("player/player_join", responseData); 
	}
	
	// 팀 소속 신청
	@PostMapping("joinTeam")
	public ModelAndView joinTeam(Player player, HttpSession session) {
		log.info("{}", player);
		playerService.joinTeam(player);
		
		session.setAttribute("alertMsg", "팀소속이 신청되었습니다. 승인을 기다려주세요.");
		
		return mv.setViewNameAndData("redirect:/my_page", null);
		
	}
}
