package com.kh.baseball.player.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.baseball.player.model.vo.Player;

@Mapper
public interface PlayerMapper {

	void savePlayer(Player player);
	
	

}
