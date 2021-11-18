package com.vinita.groupProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinita.groupProject.models.PieChart;
import com.vinita.groupProject.repositories.PieChartRepository;

@Service
public class PieChartService {
	
	@Autowired
	PieChartRepository pieRepo;
	
	public List<PieChart> getAll(){
	return this.pieRepo.findAll();	
	}
	

}
