package com.vinita.groupProject.controllers;


import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.vinita.groupProject.models.Currency;
import com.vinita.groupProject.models.User;
import com.vinita.groupProject.services.CurrencyService;
import com.vinita.groupProject.services.TransactionService;
import com.vinita.groupProject.services.UserService;


@Controller
public class HomeController {
	@Autowired
	public UserService uService;
	@Autowired
	public CurrencyService cService;
	@Autowired
	public TransactionService tService;
	
	
	@PostConstruct
	private void postConstruct() {
		Currency currency = new Currency("USD");
		Currency currUsd = cService.createCurrency(currency);
		
		currency = new Currency("Amazon");
		Currency currAmazon = cService.createCurrency(currency);
		
		currency = new Currency("Tesla");
		Currency currTesla = cService.createCurrency(currency);
	}

	@GetMapping("/dashboard")
	public String getCurrency(Model mymodel, HttpSession session) {
		User user = this.uService.findUserById((Long)session.getAttribute("user__id"));
		mymodel.addAttribute("user", user);
		mymodel.addAttribute("balance", this.uService.getPortfolioBalance(user));
		mymodel.addAttribute("currency", user.getCurrencies());
		mymodel.addAttribute("amazon", this.uService.amazonPrice());
		mymodel.addAttribute("tesla", this.uService.tslaPrice());
//		System.out.println(this.uService.tslaPrice());
//		System.out.println(this.uService.currencyPrice("FB"));
//		System.out.println(this.uService.amazonPrice());
		return "dashboard.jsp";
	}
	
	
       
	


	
}
