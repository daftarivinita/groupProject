package com.vinita.groupProject.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinita.groupProject.models.Currency;
import com.vinita.groupProject.models.Portfolio;
import com.vinita.groupProject.repositories.PortfolioRepository;



@Service
public class PortfolioService {
	@Autowired
	private PortfolioRepository pRepo;
	@Autowired
	public CurrencyService cService;
	

	
	
	
	
	//create a portfolio
	public Portfolio createPortfolio(Portfolio portfolio) {
		//make currency
        Currency currency = cService.getCurrencyByName("USD");
        //this.cService.createCurrency(currency);
      
        Currency currency1 = cService.getCurrencyByName("Bitcoin");
        //this.cService.createCurrency(currency1);
	  

        portfolio.getHoldings().put(currency, 11000);
        portfolio.getHoldings().put(currency1, 0);
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
		
