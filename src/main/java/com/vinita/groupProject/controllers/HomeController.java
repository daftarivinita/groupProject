package com.vinita.groupProject.controllers;


import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
// some tests 
//		User user = new User("test", "ltest", "test@gmail.com", "testing123", "testing123");
//        User savedUser = uService.registerUser(user);
		
//        Currency currency = new Currency("USD");
//        Currency currUsd = cService.createCurrency(currency);
//        
//        currency = new Currency("Bitcoin");
//        Currency currBtc = cService.createCurrency(currency);

	}

	@GetMapping("/dashboard")
	public String getCurrency(Model mymodel, HttpSession session) {
		User user = this.uService.findUserById((Long)session.getAttribute("user__id"));
		mymodel.addAttribute("user", user);
		mymodel.addAttribute("balance", this.uService.getPortfolioBalance(user));
		mymodel.addAttribute("currency", user.getCurrencies());
		//this.uService.tslaPrice();
		//this.uService.currencyPrice("FB");
		return "dashboard.jsp";
	}

	
}
