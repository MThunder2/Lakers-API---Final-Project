package com.promineotech.lakers.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player {
	private int playerId;
	private String firstName;
	private String lastName;
	private int yearsPlayed;
	private int jerseyNumber;
	
	

}
