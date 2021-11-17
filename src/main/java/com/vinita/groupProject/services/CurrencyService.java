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
	
	
	///nobody make duplicate currency
	
	public List<Currency> getAllCurrency(){
		return this.cRepo.findAll();
	}
	public Currency createCurrency(Currency currency) {
		Currency existing = this.cRepo.findByName(currency.getName());
		if (existing == null) {
			return this.cRepo.save(currency);
		} else {
			return existing;
		}
	}

	//retrieve a currency
		public Currency getCurrency(Long id) {
			return this.cRepo.findById(id).orElse(null);
		}
		//update a currency
		public Currency updateCurrency(Currency currency) {
			return this.cRepo.save(currency);
		}
		
		//delete a currency
		public void destroy(Long id) {
			this.cRepo.deleteById(id);
		}

}