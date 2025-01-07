package com.kh.baseball.player.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.baseball.player.model.vo.Player;
import com.kh.baseball.player.model.vo.PlayerAttachment;

@Mapper
public interface PlayerMapper {

	void savePlayer(Player player);

	int getTotalCount();

	void savePlayerFile(PlayerAttachment playerAtt);
	
	List<Player> findAllPlayerKorean(RowBounds rowbounds);
	
	Player findPlayerDetail(int playerNo);

	int updatePlayer(Player player);

	void updatePlayerFile(PlayerAttachment playerAtt);

	List<PlayerAttachment> findAllPlayerFile();

	Player mypagePlayerDetail(int userNo);

	PlayerAttachment findPlayerFile(int playerNo);

	void deletePlayer(Player player);
	

}
