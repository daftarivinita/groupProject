package com.vinita.groupProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinita.groupProject.models.PortfolioBalance;
import com.vinita.groupProject.repositories.PortfolioBalanceRepository;

@Service
public class PortfolioBalanceService {
	@Autowired
	private PortfolioBalanceRepository pRepo;
	
	
	
	// Get all balance
	public List<PortfolioBalance> getAllBalance(){
		return pRepo.findAll();
	}
	
	//Save balance
	public PortfolioBalance saveBalance(Double balance) {
		return pRepo.save(balance);
		
	}

}
