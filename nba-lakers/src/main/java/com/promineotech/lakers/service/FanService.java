package com.promineotech.lakers.service;

import java.util.List;

import com.promineotech.lakers.entity.Fan;

public interface FanService {

	 List<Fan> fetchFans();                                                          
	  
	 List<Fan> fetchAFan(String firstName, String lastName);                            

	 List<Fan> fetchFanByFirstName(String firstName);                                   

	 Fan createFan(String firstName, String lastName, int age, String fanOf);                    
	  
	 Fan updateFan(int fanId, Fan updatedFan);                           

	}

