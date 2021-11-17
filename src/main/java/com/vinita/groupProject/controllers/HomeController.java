package com.vinita.groupProject.controllers;


import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vinita.groupProject.models.Currency;
import com.vinita.groupProject.models.Transaction;
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
	
	@GetMapping("/{id}/new")

	public String getPageForTransaction(@PathVariable("id") Long id,Model mymodel, HttpSession session,@ModelAttribute("transaction") Transaction transaction) {
		mymodel.addAttribute("user", this.uService.findUserById((Long)session.getAttribute("user__id")));
		mymodel.addAttribute("currency", this.cService.getCurrency(id));
		if(this.cService.getCurrency(id).getName().equals("Amazon")) {
						mymodel.addAttribute("price", this.uService.amazonPrice());
		}else {
			mymodel.addAttribute("price", this.uService.tslaPrice());
			
		}
		return "new.jsp";
		
	}
			
			
	@PostMapping("/{id}/new")
	
	public String createPageForTransaction(@Valid @ModelAttribute("transaction") Transaction transaction, BindingResult result, @PathVariable("id") Long id, HttpSession session, Model myModel, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "new.jsp";
		} else {
			User user = this.uService.findUserById((Long)session.getAttribute("user__id"));
			Currency targetCurrency = this.cService.getCurrency(id);
			Double price = transaction.getCurrentRate() * transaction.getCount();
			System.out.println(price);
			Map<Currency, Double> allCurrency =user.getCurrencies();
			if(transaction.getTransactionType().equals("Buy")) {
				Set<Currency> keys = allCurrency.keySet();
				for(Currency key : keys) {
					if(key.equals(targetCurrency)) {
						allCurrency.put(key, allCurrency.get(key) + transaction.getCount());
					}
					if(key.getName().equals("USD")) {
						if(allCurrency.get(key) < price) {
							redirectAttributes.addFlashAttribute("errors", "You Don't Have Enough Balance");
							return "redirect:/" +id +"/new";
						}
						allCurrency.put(key, allCurrency.get(key) - price);
					}
				}
			}else {
				Set<Currency> keys = allCurrency.keySet();
				for(Currency key : keys) {
					if(key.equals(targetCurrency)) {
						if(allCurrency.get(key)<transaction.getCount()) {
							redirectAttributes.addFlashAttribute("errors", "You don't have this many stock to sell");
							return "redirect:/" +id +"/new";
						}
						allCurrency.put(key, allCurrency.get(key) - transaction.getCount());
					}
					if(key.getName().equals("USD")) {
						allCurrency.put(key, allCurrency.get(key) + price);
					}
				}
			}
			transaction.setCurrency(targetCurrency);
			transaction.setUser(this.uService.findUserById((Long)session.getAttribute("user__id")));
			this.tService.createTransaction(transaction);
			return "redirect:/dashboard";
		}
	}
}
			