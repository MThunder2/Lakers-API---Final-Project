package com.promineotech.lakers.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import com.promineotech.lakers.entity.Fan;
import com.promineotech.lakers.service.FanService;


public class DefaultFanController implements FanController {
	
	@Autowired
	private FanService fanService;

	@Override
	public List<Fan> fetchFans() {
		return fanService.fetchFans();
	}

	@Override
	public List<Fan> fetchAFan(String firstName, String lastName) {
		return fanService.fetchAFan(firstName, lastName);
	}

	@Override
	public List<Fan> fetchFanByFirstName(String firstName) {
		return fanService.fetchFanByFirstName(firstName);
	}

	@Override
	public Fan createFan(String firstName, String lastName, int age, String fanOf) {
		return fanService.createFan(firstName, lastName, age, fanOf);
	}

	@Override
	public Fan updateFan(int fanId, @Valid Fan updatedFan) {
		return fanService.updateFan(fanId, updatedFan);
	}

}
