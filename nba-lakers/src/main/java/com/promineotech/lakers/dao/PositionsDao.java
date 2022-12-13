package com.promineotech.lakers.dao;

import java.util.List;

import com.promineotech.lakers.entity.Positions;


public interface PositionsDao {

	List<Positions> fetchPositions(int positionPk, String positionId);
	
	List<Positions> fetchAllPositions();
	
	void deletePlayer(int deleteId);
	
	Positions createPosition(int positionPk, String positionId, int playerId, String positionName);

	Positions updatePosition(int positionPk, Positions updatedPosition);
	
	}
	

