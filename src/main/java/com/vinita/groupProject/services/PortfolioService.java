package com.vinita.groupProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.vinita.groupProject.models.Portfolio;
import com.vinita.groupProject.repositories.PortfolioRepository;



@Service
public class PortfolioService {
	@Autowired
	private PortfolioRepository pRepo;

	
	
	
	
	//create a portfolio
	public Portfolio createPortfolio(Portfolio portfolio) {
		return this.pRepo.save(portfolio);
	}
	
	//retrieve a portfolio
	public Portfolio getPortfolio(Long id) {
		return this.pRepo.findById(id).orElse(null);
	}
	//update a portfolio
	public Portfolio updatePortfolio(Portfolio portfolio) {
		return this.pRepo.save(portfolio);
	}
	
	//delete a portfolio
	public void destroy(Long id) {
		this.pRepo.deleteById(id);
	}
}
		
