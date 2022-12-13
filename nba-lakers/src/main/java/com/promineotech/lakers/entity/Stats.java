package com.promineotech.lakers.entity;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stats {
	private int playerId;
	private int positionId;
	private BigDecimal pointsPerGame;
	private BigDecimal assistsPerGame;
	private BigDecimal reboundsPerGame;
	

}
