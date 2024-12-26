package com.kh.baseball.player.model.vo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.baseball.player.model.dao.PlayerMapper;
import com.kh.baseball.player.model.service.PlayerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

	private final PlayerMapper mapper;
	// private final ; 검증용 클래스 생성?
	
	
	@Override
	public void savePlayer(Player player) {
		
		// 데이터 가공 후 다오로 넘겨서 디비에 인서트
		// 익셉션핸들러 
		// 값 검증
		
		mapper.savePlayer(player);
		
		
	}

	@Override
	public List<Player> selectPlayerBoard() {
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
