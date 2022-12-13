package com.promineotech.lakers.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.lakers.entity.Player;
import com.promineotech.lakers.service.PlayerService;

@RestController
public class DefaultPlayerController implements PlayerController {
	
	@Autowired
	private PlayerService playerService;

	@Override
	public List<Player> fetchPlayers() {
		return playerService.fetchPlayers();
	}

	@Override
	public List<Player> fetchAPlayer(String firstName, String lastName) {
		return playerService.fetchAPlayer(firstName, lastName);
	}

	@Override
	public List<Player> fetchPlayerByFirstName(String firstName) {
		return playerService.fetchPlayerByFirstName(firstName);
	}

	@Override
	public Player createPlayer(String firstName, String lastName, int yearsPlayed, int jerseyNumber) {
		return playerService.createPlayer(firstName, lastName, yearsPlayed, jerseyNumber);
	}

	@Override
	public Player updatePlayer(int playerId, @Valid Player updatedPlayer) {
		return playerService.updatePlayer(playerId, updatedPlayer);
	}

}
