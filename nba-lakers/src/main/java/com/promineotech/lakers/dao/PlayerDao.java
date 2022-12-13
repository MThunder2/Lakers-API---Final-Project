package com.promineotech.lakers.dao;

import java.util.List;

import com.promineotech.lakers.entity.Player;

public interface PlayerDao {
	
	List<Player> fetchPlayer(String firstName, String lastName);
	
	List<Player> fetchAllPlayers();
	
	void deletePlayer(int deleteId);
	
	Player createPlayer(int playerId, String firstName, String lastName, int yearsPlayed,
			int jerseyNumber);

	Player updatePlayer(int playerId, Player updatedPlayer);
	
	}



