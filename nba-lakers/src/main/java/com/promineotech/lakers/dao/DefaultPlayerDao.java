package com.promineotech.lakers.dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.promineotech.lakers.entity.Player;

import lombok.extern.slf4j.Slf4j;

@Component
@Service
@Slf4j
public class DefaultPlayerDao implements PlayerDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Player> fetchPlayer(String firstName, String lastName) {
		log.info("DAO: firstName={}, lastName={}", firstName, lastName);

	//@formatter:off
		String sql = " "
				+ "SELECT * "
				+ "FROM players "
				+ "WHERE first_name = :first_name AND last_name = :last_name";
		//@formatter:on

		Map<String, Object> params = new HashMap<>();
		params.put("first_name", firstName);
		params.put("last_name", lastName);

		return jdbcTemplate.query(sql, params, new RowMapper<>() {

			@Override
			public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
			//@formatter:off
			return Player.builder()
					.playerId(rs.getInt("player_id"))
					.firstName(rs.getString("first_name"))
					.lastName(rs.getString("last_name"))
					.yearsPlayed(rs.getInt("years_played"))
					.jerseyNumber(rs.getInt("jersey_number"))
					.build();
			//@formatter:on
			}
		});
	}

	@Override
	public List<Player> fetchAllPlayers() {

		//@formatter:off
		String sql = " "
				+ "SELECT * "
				+ "FROM players ";
		//@formatter:on

		Map<String, Object> params = new HashMap<>();

		return jdbcTemplate.query(sql, params, new RowMapper<>() {

			@Override
			public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
				//@formatter:off
				return Player.builder()
						.playerId(rs.getInt("player_id"))
						.firstName(rs.getString("first_name"))
						.lastName(rs.getString("last_name"))
						.yearsPlayed(rs.getInt("years_played"))
						.jerseyNumber(rs.getInt("jersey_number"))
						.build();
				//@formatter:on
			}
		});
	}

	@Override
	public void deletePlayer(int deleteId) {
		//@formatter:off
		String sql = " "
				+ "DELETE FROM players "
				+ "WHERE player_id = :player_id";
		//@formatter:on

		Map<String, Object> params = new HashMap<>();

		params.put("player_id", deleteId);
		if (jdbcTemplate.update(sql, params) == 0)
			throw new NoSuchElementException();

	}

	@Override
	public Player createPlayer(int playerId, String firstName, String lastName, int yearsPlayed, int jerseyNumber) {
		SqlParams sqlparams = new SqlParams();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		sqlparams.sql = " " 
				+ "INSERT into players " 
				+ "(first_name, last_name, years_played, jersey_number"
				+ "VALUES (:first_name, :last_name, :years_played, :jersey_number)";
		sqlparams.source.addValue("first_name", firstName);
		sqlparams.source.addValue("last_name", lastName);
		sqlparams.source.addValue("years_player", yearsPlayed);
		sqlparams.source.addValue("jersey_number", jerseyNumber);

		jdbcTemplate.update(sqlparams.sql, sqlparams.source, keyHolder);
		return Player.builder()
				.playerId(keyHolder.getKey().intValue())
				.firstName(firstName).lastName(lastName)
				.yearsPlayed(yearsPlayed)
				.jerseyNumber(jerseyNumber)
				.build();
	}

	class SqlParams {
		String sql;
		MapSqlParameterSource source = new MapSqlParameterSource();
	}

	@Override
	public Player updatePlayer(int playerId, Player updatedPlayer) {
		//@formatter:off
		String sql = " "
				+ "UPDATE players "
				+ "SET "
				+ "first_name = :first_name, "
				+ "last_name = :last_name, "
				+ "years_played = :years_played, "
				+ "jersey_number = :jersey_number "
				+ "WHERE player_id = :player_id";
		//@formatter:on

		Map<String, Object> params = new HashMap<>();
		params.put("first_name", updatedPlayer.getFirstName());
		params.put("last_name", updatedPlayer.getLastName());
		params.put("years_played", updatedPlayer.getYearsPlayed());
		params.put("jersey_number", updatedPlayer.getJerseyNumber());
		params.put("player_id", playerId);

		if (jdbcTemplate.update(sql, params) == 0) {
			throw new NoSuchElementException("failed to update ");
		}

		return Player.builder()
				.playerId(playerId)
				.firstName(updatedPlayer.getFirstName())
				.lastName(updatedPlayer.getLastName())
				.yearsPlayed(updatedPlayer
				.getYearsPlayed())
				.jerseyNumber(updatedPlayer.getJerseyNumber())
				.build();

	}

}