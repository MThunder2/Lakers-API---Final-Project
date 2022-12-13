package com.promineotech.lakers.dao;

import java.util.List;

import com.promineotech.lakers.entity.Fan;

public interface FanDao {
	List<Fan> fetchFans(String firstName, String lastName);
	
	List<Fan> fetchAllFans();
	
	void deleteFan(int deleteId);
	
	Fan createFan(String firstName, String lastName, int age, String fanOf);
	
	Fan updateFan(int fanId, Fan updatedFan);
	
}
