package com.woniuxy.service;

import java.util.List;

import com.woniuxy.pojo.Information;
import com.woniuxy.pojo.Person;

public interface InformationService {
	
	public List<Information> allInformation();

	public List<Information> searchInformations(Person person);
}
