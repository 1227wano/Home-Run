package com.kh.baseball.player.model.service;

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
import com.kh.baseball.exception.PlayerNotFoundException;
import com.kh.baseball.player.model.dao.PlayerMapper;
import com.kh.baseball.player.model.vo.Player;
import com.kh.baseball.player.model.vo.PlayerAttachment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

	private final PlayerMapper mapper;
	private final ServletContext context;
	
	// get 총 player 수
	private int getTotalCount() {
		int totalCount = mapper.getTotalCount();

		if (totalCount == 0) {
			throw new PlayerNotFoundException("등록된 선수가 없습니다");
		}
		return totalCount;
	}
	// get 페이지 정보
	private PageInfo getPageInfo(int totalCount, int page) {
		return Pagination.getPageInfo(totalCount, page, 5, 5);
	}
	// get 전체 player 정보
	private List<Player> getPlayerList(PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowbounds = new RowBounds(offset, pi.getBoardLimit()); 
		return mapper.findAllPlayerKorean(rowbounds);
	}
	
	
	// 선수 등록
	@Override
	public void savePlayer(Player player, MultipartFile upfile) {
		
		// 익셉션핸들러(handler) & 값 검증(validate) 해야됨
		mapper.savePlayer(player);
		
		// 파일 유무 체크 / 업로드
		if(!("".equals(upfile.getOriginalFilename()))) {
			
			PlayerAttachment playerAtt = new PlayerAttachment();
			handleFileUpload(playerAtt, upfile);
			
			mapper.savePlayerFile(playerAtt);
		}
	}

	
	// 파일 업로드
	private void handleFileUpload(PlayerAttachment playerAtt, MultipartFile upfile) {
		
		String fileName = upfile.getOriginalFilename();
		String ext = fileName.substring(fileName.lastIndexOf("."));
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		int randomNum = (int)(Math.random() * 90000) + 10000;
		String changeName = currentTime + randomNum + ext;
		
		String savePath = context.getRealPath("resources/upload_files/");
		
		try {
			upfile.transferTo(new File(savePath + changeName));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			// throw new FailToFileUploadException("파일 이상해");	 이거 익셉션 만들어서 넣기..?
		}
		// 첨부파일이 존재 => 업로드 + PlayerAttachment객체에 originName, changeName 
		playerAtt.setOriginName(fileName);
		playerAtt.setChangeName("/resources/upload_files/" + changeName);
	}

	
	// 전체 선수 조회(가나다순)
	@Override
	public Map<String, Object> findAllPlayerKorean(int currentPage) {
		
		// 총 개수 == DB 조회
		// 요청 페이지 == currentPage
		// 첫 페이지에 선수 5명 -> 더보기버튼으로 5명씩 추가 셀렉
		int totalCount = getTotalCount();
		
		PageInfo pi = getPageInfo(totalCount, currentPage);
		
		List<Player> players = getPlayerList(pi);
		// System.out.print(players);
		Map<String, Object> map = new HashMap();
		map.put("players", players);
		map.put("PageInfo", pi);
		
		return map;
	}

	
	// 선수 더보기
	@Override
	public List<Player> findMorePlayer(int page) {
		
		int totalCount = getTotalCount();
		PageInfo pi = getPageInfo(totalCount, page);
		
		List<Player> players = getPlayerList(pi);
		
		return players;
	}
	
	/* 전체 선수 조회(선수 조회수순)
	@Override
	public Map<String, Object> findAllPlayerCount(int currentPage) {
		return null;
	}
	*/
	
	// 선수 상세보기
	@Override
	public Map<String, Object> selectPlayer(int playerNo) {
		
		Player player = findPlayerDetail(playerNo);
		// log.info("{}", player);
		Map<String, Object> responseData = new HashMap();
		responseData.put("player", player);
		
		return responseData;
	}
	private Player findPlayerDetail(int playerNo) {
		Player player = mapper.findPlayerDetail(playerNo);
		// log.info("{}", player);

		if(player == null) {
			throw new PlayerNotFoundException("선수를 찾을 수 없습니다.");
		}
		return player;
	}

	
	@Override
	public int updatePlayer(Player player) {
		return 0;
	}

	@Override
	public int deletePlayer(int userNo) {
		return 0;
	}


	

	

}
