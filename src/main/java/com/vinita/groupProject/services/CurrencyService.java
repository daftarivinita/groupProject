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
	
	//create a currency
		public Currency createCurrency(Currency currency) {
			Currency existing = this.cRepo.findByName(currency.getName());
			if (existing == null) {
				return this.cRepo.save(currency);
			} else {
				return existing;
			}
		}
		public List<Currency> getAllCurrency(){
			return this.cRepo.findAll();
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
		
		//get Currency by name
		public Currency getCurrencyByName(String name) {
			Currency target = this.cRepo.findByName(name);
			return target;
		}

	
}
		
