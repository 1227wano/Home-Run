package com.kh.baseball.player.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.baseball.common.PageInfo;
import com.kh.baseball.player.model.vo.Player;

@Mapper
public interface PlayerMapper {

	void savePlayer(Player player);

	int getTotalCount();

	List findAllPlayer(RowBounds rowbounds);
	
	

}
