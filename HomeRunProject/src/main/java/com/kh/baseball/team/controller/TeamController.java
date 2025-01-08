package com.kh.baseball.team.controller;

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
import com.kh.baseball.player.model.vo.Player;
import com.kh.baseball.team.model.service.TeamService;
import com.kh.baseball.team.model.vo.Team;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TeamController {
	
	private final TeamService teamService;
	private final ModelAndViewUtil mv;
	
	// 팀창설 신청 양식 열어
	@GetMapping("insertTeamform")
	public String teamform() {
		return "team/team_enroll";	 
	}
	
	// 팀 창설 신청
	@PostMapping("insertTeam")
	public ModelAndView insertTeam(Team team, MultipartFile upfile, HttpSession session) {
		teamService.insertTeam(team, upfile);
		session.setAttribute("alertMsg", "팀 창설이 성공적으로 신청되었습니다. 승인을 기다려주세요.");	
		return mv.setViewNameAndData("redirect:/", null);
	}

	// 전체팀정보 조회
	@GetMapping("selectAllTeam")
	public ModelAndView selectAllTeam(Team team, @RequestParam(value="page", defaultValue="1") int page) {
		Map<String, Object> map = teamService.selectAllTeam(page);
		return mv.setViewNameAndData("team/teamList", map); 
	}
	
	// 팀 상세보기
	@GetMapping("selectTeam/{teamNo}")
	public ModelAndView forwardPlayerList(@PathVariable(name="teamNo") int teamNo) {
		Map<String, Object> responseData = teamService.selectTeam(teamNo);
		return mv.setViewNameAndData("team/teamDetail", responseData);
	}
	
	/* 마이페이지에서 선수및팀 관련 정보로 넘기는데 선수 상세정보 같이 넘기기
	@GetMapping("playerAndTeam/{userNo}")
	public ModelAndView playerAndTeam(@PathVariable(name="userNo") int userNo) {
		
		Map<String, Object> responseData = playerService.mypagePlayer(userNo);
		
		return mv.setViewNameAndData("player/playerAndTeam", responseData);
	}
	*/
	
	// 팀수정 양식 열어
	@GetMapping("updateTeamform")
	public ModelAndView updateform(int teamNo) {
		Map<String, Object> responseData = teamService.selectTeam(teamNo);
		return mv.setViewNameAndData("team/team_update", responseData); 
	}
		
	// 팀정보 수정 신청
	@PostMapping("updateTeam")
	public ModelAndView updateTeam(Team team, MultipartFile upfile, HttpSession session) {
		// log.info("{}", team);
		teamService.updateTeam(team, upfile);
		
		session.setAttribute("alertMsg", "팀정보 수정 성공");
		
		return mv.setViewNameAndData("redirect:/playerAndTeam/" + team.getTeamNo(), null); // 되나?
		
	}
	
	// 팀소속 신청 양식 열어
	@GetMapping("joinTeamform")
	public String joinTeamform() {
		return "team/join-team_enroll";	 
	}
	
	// 팀 소속 신청  : player의 TEAM_NAME을 입력한
	
	// 선수 삭제
	@PostMapping("deleteTeam")
	public ModelAndView deleteTeam(int teamNo, HttpSession session) {
		teamService.deleteTeam(teamNo);
		session.setAttribute("alertMsg", "선수 정보 삭제 성공");
		return mv.setViewNameAndData("redirect:/playerAndTeam/" + teamNo, null);
	
	}
}
