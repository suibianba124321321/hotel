package com.woniuxy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.pojo.Information;
import com.woniuxy.pojo.Person;
import com.woniuxy.service.InformationService;

@Controller
@RequestMapping("/information")
public class InformationController {
	@Resource
	private InformationService informationService;
	
	public InformationService getInformationService() {
		return informationService;
	}

	public void setInformationService(InformationService informationService) {
		this.informationService = informationService;
	}

	@RequestMapping("search")
	@ResponseBody
	public List<Information> searchInformations(Person person){
		List<Information> informations=informationService.searchInformations(person);
		return informations;
	}
}
