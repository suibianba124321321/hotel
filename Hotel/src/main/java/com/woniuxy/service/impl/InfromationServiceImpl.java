package com.woniuxy.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.woniuxy.dao.InformationDAO;
import com.woniuxy.dao.PersonDAO;
import com.woniuxy.pojo.Information;
import com.woniuxy.pojo.Person;
import com.woniuxy.service.InformationService;
@Service("informationServcie")
public class InfromationServiceImpl implements InformationService{
	@Resource
	private InformationDAO informationDAO;
	@Resource
	private PersonDAO personDAO;
	
	public InformationDAO getInformationDAO() {
		return informationDAO;
	}

	public void setInformationDAO(InformationDAO informationDAO) {
		this.informationDAO = informationDAO;
	}

	public PersonDAO getPersonDAO() {
		return personDAO;
	}

	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	@Override
	public List<Information> allInformation() {
		
		List<Information> informations=informationDAO.findAllInformation();
		for(int i=0;i<informations.size();i++){
			Information information=informations.get(i);
			Person person=personDAO.findPersonById(information.getPerson_id());
			informations.get(i).setPerson(person);
			
		}
		
		return informations;
	}

	@Override
	public List<Information> searchInformations(Person person) {
		List<Information> informations=new ArrayList<>();
		List<Person> persons=personDAO.findPersonBySome(person);
		for(int i=0;i<persons.size();i++){
			Person nowPerson=persons.get(i);
			List<Information> now=informationDAO.findInfomationsByPerson(nowPerson);
			if(now!=null && now.size()>0){
				for(int j=0;j<now.size();j++){
					now.get(j).setPerson(nowPerson);
				}
				informations.addAll(now);
			}
			
		}
		return informations;
	}

}
