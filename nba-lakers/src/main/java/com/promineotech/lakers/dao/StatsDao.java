package com.promineotech.lakers.dao;

import java.math.BigDecimal;
import java.util.List;

import com.promineotech.lakers.entity.Stats;

public interface StatsDao {
	
	List<Stats> fetchStats(int playerId);
	
	Stats updateStats(int playerId, BigDecimal pointsPerGame, 
			BigDecimal assistsPerGame, BigDecimal reboundsPerGame);

}
