package com.kh.baseball.player.model.vo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.baseball.player.model.service.PlayerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

	@Override
	public int savePlayer(Player player) {
		
		
		
		
		
		
		
		
		return 0;
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
