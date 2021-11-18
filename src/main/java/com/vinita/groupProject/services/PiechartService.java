package com.vinita.groupProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinita.groupProject.models.Piechart;
import com.vinita.groupProject.repositories.PiechartRepository;

@Service
public class PiechartService {
	@Autowired 
	
	private PiechartRepository pieRepo;

	public List<Piechart> getAll(){
		return this.pieRepo.findAll();
	}
}
