package com.promineotech.lakers.service;

import java.util.List;

import com.promineotech.lakers.entity.Player;


public interface PlayerService {

	 List<Player> fetchPlayers();                                                          
	  
	 List<Player> fetchAPlayer(String firstName, String lastName);                            

	 List<Player> fetchPlayerByFirstName(String firstName);                                   

	 Player createPlayer(String firstName, String lastName, int yearsPlayed, int jerseyNumber);                    
	  
	 Player updatePlayer(int playerId, Player updatedPlayer);                           

	}
