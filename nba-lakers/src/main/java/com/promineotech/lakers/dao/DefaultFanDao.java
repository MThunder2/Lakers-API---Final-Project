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

import com.promineotech.lakers.entity.Fan;

import lombok.extern.slf4j.Slf4j;

@Service
@Component
@Slf4j
public class DefaultFanDao implements FanDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Fan> fetchFans(String firstName, String lastName) {
		log.debug("DAO: first_name={}, last_name={}", firstName, lastName);

		//@formatter:off
		String sql = " "
				+ "SELECT * "
				+ "FROM fans "
				+ "WHERE first_name = :first_name AND last_name = :last_name";
		//@formatter:on

		Map<String, Object> params = new HashMap<>();
		params.put("first_name", firstName);
		params.put("last_name", lastName);

		return jdbcTemplate.query(sql, params, new RowMapper<>() {

			@Override
			public Fan mapRow(ResultSet rs, int rowNum) throws SQLException {
			//@formatter:off
			return Fan.builder()
					.fanId(rs.getInt("fan_id"))
					.firstName(rs.getString("first_name"))
					.lastName(rs.getString("last_name"))
					.age(rs.getInt("age"))
					.fanOf(rs.getString("fan_of"))
					.build();
			//@formatter:on
			}
		});
	}

	@Override
	public List<Fan> fetchAllFans() {
		//@formatter:off
			String sql = " "
				+ "SELECT * "
				+ "FROM fans ";
			//@formatter:on

		Map<String, Object> params = new HashMap<>();

		return jdbcTemplate.query(sql, params, new RowMapper<>() {

			@Override
			public Fan mapRow(ResultSet rs, int rowNum) throws SQLException {
			//@formatter:off
				return Fan.builder()
						.fanId(rs.getInt("fan_id"))
						.firstName(rs.getString("first_name"))
						.lastName(rs.getString("last_name"))
						.age(rs.getInt("age"))
						.fanOf(rs.getString("fan_of"))
						.build();
				//@formatter:on
			}
		});
	}

	@Override
	public void deleteFan(int deleteId) {
		//@formatter:off
				String sql = " "
						+ "DELETE FROM fans "
						+ "WHERE fan_id = :fan_id";
				//@formatter:on

		Map<String, Object> params = new HashMap<>();

		params.put("fan_id", deleteId);
		if (jdbcTemplate.update(sql, params) == 0)
			throw new NoSuchElementException();

	}

	@Override
	public Fan createFan(String firstName, String lastName, int age, String fanOf) {
		SqlParams sqlparams = new SqlParams();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		sqlparams.sql = " " 
				+ "INSERT into fan " 
				+ "(first_name, last_name, age, fan_of"
				+ "VALUES (:first_name, :last_name, :age, :fan_of)";
		sqlparams.source.addValue("first_name", firstName);
		sqlparams.source.addValue("last_name", lastName);
		sqlparams.source.addValue("age", age);
		sqlparams.source.addValue("fan_of", fanOf);

		jdbcTemplate.update(sqlparams.sql, sqlparams.source, keyHolder);
		return Fan.builder()
				.fanId(keyHolder.getKey().intValue())
				.firstName(firstName)
				.lastName(lastName)
				.age(age)
				.fanOf(fanOf)
				.build();
	}

	class SqlParams {
		String sql;
		MapSqlParameterSource source = new MapSqlParameterSource();
	}

	@Override
	public Fan updateFan(int fanId, Fan updatedFan) {
		//@formatter:off
				String sql = " "
						+ "UPDATE fans "
						+ "SET "
						+ "first_name = :first_name, "
						+ "last_name = :last_name, "
						+ "age = :age, "
						+ "fan_of = :fan_of "
						+ "WHERE fan_id = :fan_id";
				//@formatter:on

		Map<String, Object> params = new HashMap<>();
		params.put("first_name", updatedFan.getFirstName());
		params.put("last_name", updatedFan.getLastName());
		params.put("age", updatedFan.getAge());
		params.put("fan_of", updatedFan.getFanOf());
		params.put("fan_id", fanId);

		if (jdbcTemplate.update(sql, params) == 0) {
			throw new NoSuchElementException("failed to update ");
		}

		return Fan.builder()
				.playerId(fanId).firstName(updatedFan.getFirstName())
				.lastName(updatedFan.getLastName())
				.age(updatedFan.getAge())
				.fanOf(updatedFan.getFanOf())
				.build();

	}

}
