package com.kh.baseball.team.model.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.baseball.common.PageInfo;
import com.kh.baseball.common.Pagination;
import com.kh.baseball.exception.BoardNoValueException;
import com.kh.baseball.exception.FailToFileUploadException;
import com.kh.baseball.exception.TeamNotFoundException;
import com.kh.baseball.player.model.dao.PlayerMapper;
import com.kh.baseball.team.model.dao.TeamMapper;
import com.kh.baseball.team.model.vo.Team;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

	private final TeamMapper mapper;
	private final PlayerMapper Pmapper;
	private final ServletContext context;
	
	@Override
	public void insertTeam(Team team, MultipartFile upfile) {
		validateTeam(team);
		if(!("".equals(upfile.getOriginalFilename()))) {
			handleFileUpload(team, upfile);
			mapper.insertTeam(team);
			// 플레이어의 팀네임을 insert한 팀네임으로 수정
			Pmapper.updatePlayerTeam(team);
		}
	}
	private void validateTeam(Team team) {
		if (team == null || 
			team.getTeamIntro() == null || 
			team.getTeamIntro().trim().isEmpty()) {
			throw new BoardNoValueException("팀소개글의 입력값이 부적절합니다.");
		}
		String teamIntro = escapeHtml(team.getTeamIntro());
		team.setTeamIntro(convertNewlineToBr(teamIntro));
	} 
	private String escapeHtml(String value) {
		return value.replaceAll("<", "&lt;")
					.replaceAll(">", "&gt;");
	}
	private String convertNewlineToBr(String value) {
		return value.replaceAll("\n", "<br>");
	}

	private void handleFileUpload(Team team, MultipartFile upfile) {
		String fileName = upfile.getOriginalFilename();
		String ext = fileName.substring(fileName.lastIndexOf("."));
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		int randomNum = (int)(Math.random() * 90000) + 10000;
		String changeName = currentTime + randomNum + ext;
		String savePath = context.getRealPath("resources/upload_files/");
		try {
			upfile.transferTo(new File(savePath + changeName));
		} catch (IllegalStateException | IOException e) {
			throw new FailToFileUploadException("파일 형식이 그릇되었습니다.");
		}
		team.setOriginName(fileName);
		team.setChangeName("/resources/upload_files/" + changeName);
	}

	@Override
	public Map<String, Object> selectAllTeam(int currentPage) {
		int totalCount = getTotalCount();
		PageInfo pi = getPageInfo(totalCount, currentPage);
		
		List<Team> teams = getTeamList(pi);
		// log.info("{}" + teams);
		Map<String, Object> map = new HashMap();
		map.put("teams", teams);
		map.put("PageInfo", pi);
		return map;
	}
	
	private int getTotalCount(){
		int totalCount = mapper.getTotalCount();
		if (totalCount == 0) {
			throw new TeamNotFoundException("등록된 팀이 없습니다");
		}
		return totalCount;
	}

	private PageInfo getPageInfo(int totalCount, int page) {
		return Pagination.getPageInfo(totalCount, page, 5, 5);
	}

	private List<Team> getTeamList(PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowbounds = new RowBounds(offset, pi.getBoardLimit()); 
		return mapper.selectAllTeam(rowbounds);
	}

	@Override
	public Map<String, Object> selectTeam(int teamNo) {
		Team team = selectTeamDetail(teamNo);
		Map<String, Object> responseData = new HashMap();
		responseData.put("team", team);
		return responseData;
	}

	private Team selectTeamDetail(int teamNo) {
		Team team = mapper.selectTeamDetail(teamNo);
		if(team == null) {
			throw new TeamNotFoundException("팀을 찾을 수 없습니다.");
		}
		return team;
	}
	
	@Override
	public void updateTeam(Team team, MultipartFile upfile) {
		
	}

	@Override
	public void deleteTeam(int teamNo) {
		
	}

}
