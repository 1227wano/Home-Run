package com.kh.baseball.player.model.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.baseball.exception.PlayerNotFoundException;
import com.kh.baseball.player.model.dao.PlayerMapper;
import com.kh.baseball.player.model.vo.Player;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

	private final PlayerMapper mapper;
	private final ServletContext context;
	
	private int getTotalCount() {
		int totalCount = mapper.selectTotalCount();

		if (totalCount == 0) {
			throw new PlayerNotFoundException("등록된 선수가 없습니다");
		}
		return totalCount;
	}
	
	@Override
	public void savePlayer(Player player, MultipartFile upfile) {
		
		// 익셉션핸들러 & 값 검증
		// 파일 유무 체크 / 업로드
		
		if(!("".equals(upfile.getOriginalFilename()))) {
			
			handleFileUpload(player, upfile);
			
		}
		mapper.savePlayer(player);
	}

	private void handleFileUpload(Player player, MultipartFile upfile) {
		
		String fileName = upfile.getOriginalFilename();
		String ext = fileName.substring(fileName.lastIndexOf("."));
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		int randomNum = (int) Math.random() * 90000 + 10000;
		String changeName = currentTime + randomNum + ext;
		
		String savePath = context.getRealPath("/resources/upload_files");
		
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
	public Map<String, Object> findAllPlayer(int currentPage) {
		
		// 총 개수 == DB 조회
		// 요청 페이지 == currentPage
		// 첫 페이지에 선수 몇명? == 15명 -> 더보기버튼으로 15명씩 추가 셀렉
		int totalCount = getTotalCount();
		
		
		
		
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
