package com.promineotech.lakers.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Positions {
	private int positionPk;
	private String positionId;
	private int playerId;
	private String positionName;

}
