package com.vinita.groupProject.services;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinita.groupProject.models.Currency;
import com.vinita.groupProject.repositories.CurrencyRepository;

@Service
public class CurrencyService {
	@Autowired
	private CurrencyRepository cRepo;
	
	public List<Currency> getAllCurrencies(){
		   return this.cRepo.findAll();
	}
}
		
