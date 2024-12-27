package com.kh.baseball.player.model.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
			
			// 익셉션 만들면 throw new PlayerNotFoundException("등록된 선수 없어");
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
	public Map<String, Object> findAllPlayer() {
		
		// 리스트가 비어있는 경우
		// 
		
		
		
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
