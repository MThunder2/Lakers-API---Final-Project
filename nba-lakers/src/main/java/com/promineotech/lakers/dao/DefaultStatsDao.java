package com.promineotech.lakers.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.promineotech.lakers.entity.Stats;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultStatsDao implements StatsDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Stats> fetchStats(int playerId) {
		log.info("DAO: playerId={}", playerId);
		
		//@formatter:off
		String sql = " "
				+ "SELECT * "
				+ "FROM stats "
				+ "WHERE player_id = :player_id";
		//@formatter:on
		
		Map<String, Object> params = new HashMap<>();
		params.put("player_id", playerId);

		return jdbcTemplate.query(sql, params, new RowMapper<>() {
			
		
		@Override
		public Stats mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			//@formatter:off
			return Stats.builder()
					.playerId(rs.getInt("player_id"))
					.pointsPerGame(rs.getBigDecimal("points_per_game"))
					.assistsPerGame(rs.getBigDecimal("assists_per_game"))
					.reboundsPerGame(rs.getBigDecimal("rebounds_per_game"))
					.build();
			//@formatter:on
		}});
		
	}
	
	@Override
	public Stats updateStats(int playerId, BigDecimal pointsPerGame, BigDecimal assistsPerGame,
			BigDecimal reboundsPerGame) {
		log.info("DAO: playerId={}, pointsPerGame={}, assistsPerGame={}, reboundsPerGame={}",
				playerId, pointsPerGame, assistsPerGame, reboundsPerGame);
		
		//@formatter:off
		String sql = " "
				+ "UPDATE stats SET pointsPerGame = :points_per_game,"
				+ "assistsPerGame = :assiste_per_game, "
				+ "reboundsPerGame = :rebounds_per_game "
				+ "WHERE playerId = :player_id AND"
				+ "pointsPerGame = :points_per_game AND"
				+ "assistsPerGame = :assists_per_game AND"
				+ "reboundsPerGame = :rebounds_per_game";
		//@formatter:on
		
		Map<String, Object> params = new HashMap<>();
		params.put("player_id", playerId);
		params.put("points_per_game", pointsPerGame);
		params.put("assists_per_game", assistsPerGame);
		params.put("rebounds_per_game", reboundsPerGame);
		
		if (jdbcTemplate.update(sql, params) == 0) {
			throw new NoSuchElementException("failed to update ");
			
		}
			
		return Stats.builder()
				.playerId(playerId)
				.pointsPerGame(pointsPerGame)
				.assistsPerGame(assistsPerGame)
				.reboundsPerGame(reboundsPerGame)
				.build();
		}

	}
	

