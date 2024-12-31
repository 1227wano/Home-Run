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

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

	private final PlayerMapper mapper;
	private final ServletContext context;
	
	private int getTotalCount() {
		int totalCount = mapper.getTotalCount();

		if (totalCount == 0) {
			throw new PlayerNotFoundException("등록된 선수가 없습니다");
		}
		return totalCount;
	}
	
	private PageInfo getPageInfo(int totalCount, int page) {
		return Pagination.getPageInfo(totalCount, page, 5, 5);
	}
	
	private List<Player> getPlayerList(PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowbounds = new RowBounds(offset, pi.getBoardLimit()); 
		return mapper.findAllPlayerKorean(rowbounds);
	}
	
	
	
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

	private void handleFileUpload(PlayerAttachment playerAtt, MultipartFile upfile) {
		
		String fileName = upfile.getOriginalFilename();
		String ext = fileName.substring(fileName.lastIndexOf("."));
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		int randomNum = (int)(Math.random() * 90000) + 10000;
		String changeName = currentTime + randomNum + ext;
		
		String savePath = context.getRealPath("/resources/upload_files/");
		
		try {
			upfile.transferTo(new File(savePath + changeName));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			// throw new FailToFileUploadException("파일 이상해");	 이거 익셉션 만들어서 넣기..?
		}
		
	}

	@Override
	public Map<String, Object> findAllPlayerKorean(int currentPage) {
		
		// 총 개수 == DB 조회
		// 요청 페이지 == currentPage
		// 첫 페이지에 선수 몇명? == 10명 -> 더보기버튼으로 10명씩 추가 셀렉
		int totalCount = getTotalCount();
		
		PageInfo pi = getPageInfo(currentPage, totalCount);
		
		List<Player> players = getPlayerList(pi);
		
		Map<String, Object> map = new HashMap();
		map.put("players", players);
		map.put("PageInfo", pi);
		
		return map;
	}

	@Override
	public Map<String, Object> findAllPlayerCount(int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Player selectPlayer(int userNo) {
		return null;
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
