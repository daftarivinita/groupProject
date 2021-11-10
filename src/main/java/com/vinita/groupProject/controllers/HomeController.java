package com.vinita.groupProject.controllers;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.vinita.groupProject.models.Currency;
import com.vinita.groupProject.models.Portfolio;
import com.vinita.groupProject.models.Transaction;
import com.vinita.groupProject.models.User;
import com.vinita.groupProject.services.CurrencyService;
import com.vinita.groupProject.services.PortfolioService;
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
	@Autowired
	public PortfolioService pService;
	
//	@PostConstruct
//    private void postConstruct() {
//
//		User user = new User("test", "ltest", "test@gmail.com", "testing123", "testing123");
//        User savedUser = uService.registerUser(user);
//        
//        Currency currency = new Currency("USD");
//        Currency currUsd = cService.createCurrency(currency);
//        
//        currency = new Currency("Bitcoin");
//        Currency currBtc = cService.createCurrency(currency);
//        
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
//    }
	
	
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
	public String getCurrency(Model mymodel, HttpSession session,@ModelAttribute("portfolio") Portfolio portfolio) {
		User user = this.uService.findUserById((Long)session.getAttribute("user__id"));
		mymodel.addAttribute("user", user);
		if(user.getPortfolio() != null) {
			mymodel.addAttribute("currency", user.getPortfolio().getHoldings());
		}
//		System.out.println(user.getPortfolio().getHoldings());
		return "dashboard.jsp";
	}
		
		
	@PostMapping("/dashboard")
	public String createPortfolio(@Valid @ModelAttribute("portfolio") Portfolio portfolio, BindingResult result, HttpSession session, Model myModel) {
		if (result.hasErrors()) {
			return "dashboard.jsp";
		} else {
			portfolio.setUser(this.uService.findUserById((Long)session.getAttribute("user__id")));
			this.pService.createPortfolio(portfolio);
			return "redirect:/dashboard";
		}
			
        	
        	

		
		
	}
	
	@GetMapping("/{id}/new")
	public String usertolog(@PathVariable("id") Long id,Model mymodel, HttpSession session,@ModelAttribute("transaction") Transaction transaction) {
		mymodel.addAttribute("user", this.uService.findUserById((Long)session.getAttribute("user__id")));
		mymodel.addAttribute("currency", this.cService.getCurrency(id));
		Unirest.config().verifySsl(false);
		HttpResponse<JsonNode> response = Unirest.get("https://api.polygon.io/v1/open-close/AAPL/2020-10-14?adjusted=true&apiKey=Il4TXhi_rLZBjI3VC0zSFRmrBhhx3wsD").asJson();
		JSONObject resultFromApi = response.getBody().getObject();
		double openPrice =Double.parseDouble(resultFromApi.getString("open"));
		
		mymodel.addAttribute("price", openPrice);
		
		//get real time price here//call api
		return "new.jsp";
	}
	@PostMapping("/{id}/new")
	public String addTask(@Valid @ModelAttribute("transaction") Transaction transaction, BindingResult result, @PathVariable("id") Long id, HttpSession session, Model myModel) {
		if (result.hasErrors()) {
			return "new.jsp";
		} else {
		//get two currency // check the type// if add and subtract the currency count
		User user = this.uService.findUserById((Long)session.getAttribute("user__id"));
		//Currency cash = this.cService.getCurrencyByName("Cash");
		Currency targetCurrency = this.cService.getCurrency(id);
		Double price = transaction.getExchangeRate() * transaction.getCount();
		System.out.println(price);
		Map<Currency, Integer> allCurrency =user.getPortfolio().getHoldings();
		if(transaction.getTransactionType().equals("Buy")) {
			Set<Currency> keys = allCurrency.keySet();
			for(Currency key : keys) {
				if(key.equals(targetCurrency)) {
					allCurrency.put(key, allCurrency.get(key) + transaction.getCount());
					
				}
				if(key.getName().equals("USD")) {
					allCurrency.put(key, allCurrency.get(key) - transaction.getCount());
				}
	              
	        }
			
		}
//				for(int i =0; i<allCurrency.size(); i++) {
//					if(allCurrency.get(i).equals(targetCurrency)) {
//						allCurrency.get(i).setCount(targetCurrency.getCount() + transaction.getCount());
//						this.cService.updateCurrency(targetCurrency);
//					}
//					if(allCurrency.get(i).getName().equals("Cash")) {
//						allCurrency.get(i).setCount(targetCurrency.getCount() - transaction.getCount());
//						this.cService.updateCurrency(targetCurrency);
//					}
//				}
//				
//			}
		
		transaction.setUser(this.uService.findUserById((Long)session.getAttribute("user__id")));
		
		this.tService.createTransaction(transaction);
		return "redirect:/dashboard";
	}
}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
}
