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

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONObject;



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
        
//        Portfolio portfolio = new Portfolio();
//        portfolio.setUser(savedUser);
//        portfolio.getHoldings().put(currUsd, 800);
//        portfolio.getHoldings().put(currBtc, 100);
//        pService.createPortfolio(portfolio);
//        
//        Transaction t = new Transaction();
//        t.setUser(savedUser);
//        t.setCurrency(currBtc);
//        t.setTransactionType("Buy");
//        t.setCount(5);
//        t.setExchangeRate(10d);
//        tService.createTransaction(t);
    }
	
	
	@GetMapping("/api")
	public String testApi(Model mymodel, HttpSession session) throws UnirestException {
		System.out.println(111111111);
		Unirest.config().verifySsl(false);
		HttpResponse<JsonNode> response = Unirest.get("https://api.polygon.io/v1/open-close/AAPL/2020-10-14?adjusted=true&apiKey=Il4TXhi_rLZBjI3VC0zSFRmrBhhx3wsD").asJson();
		
		JSONObject resultFromApi = response.getBody().getObject();
		System.out.println(resultFromApi);
		
		mymodel.addAttribute("user", this.uService.findUserById((Long)session.getAttribute("user__id")));
		
		return "api.jsp";
	}


	@GetMapping("/dashboard")
	public String getCurrency(Model mymodel, HttpSession session) {
		User user = this.uService.findUserById((Long)session.getAttribute("user__id"));
		mymodel.addAttribute("user", user);
		mymodel.addAttribute("currency", user.getCurrencies());

		System.out.println(java.time.LocalDate.now());  
		
		
//		Unirest.config().verifySsl(false);
//		HttpResponse<JsonNode> response = Unirest.get("https://api.polygon.io/v1/open-close/AAPL/" +"2020-10-14" +"?adjusted=true&apiKey=Il4TXhi_rLZBjI3VC0zSFRmrBhhx3wsD").asJson();
//		JSONObject resultFromApi = response.getBody().getObject();
//		double openPrice =Double.parseDouble(resultFromApi.getString("open"));
//		System.out.println(openPrice);
//		mymodel.addAttribute("price", openPrice);
		return "dashboard.jsp";
	}
		

	
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
}
