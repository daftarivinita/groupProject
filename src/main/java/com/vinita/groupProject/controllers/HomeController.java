package com.vinita.groupProject.controllers;

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
import com.vinita.groupProject.models.Transaction;
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
//	@PostMapping("/new")
//	public String addTask(@Valid @ModelAttribute("transaction") Transaction transaction, BindingResult result,  HttpSession session, Model myModel) {
//		if (result.hasErrors()) {
//			myModel.addAttribute("allUser",this.uService.getAllUser());
//            return "new.jsp";
//        } else {
////        	task.setTaskCreatedBy(this.uService.findUserById((Long)session.getAttribute("user__id")));
////        	//task.setPriority(null)
//////            this.tService.createEntity(task);
//            return "redirect:/task";
//        }
//	}
	@GetMapping("/dashboard")
	public String getCurrency(Model mymodel, HttpSession session,@ModelAttribute("transaction") Transaction transaction) {
		mymodel.addAttribute("user", this.uService.findUserById((Long)session.getAttribute("user__id")));
		mymodel.addAttribute("currency", this.cService.allCurrency());
		
		return "dashboard.jsp";
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
			myModel.addAttribute("allUser",this.uService.getAllUser());
            return "new.jsp";
        } else {
        	Currency targetCurrency = this.cService.getCurrency(id);
        	
        	transaction.setUser(this.uService.findUserById((Long)session.getAttribute("user__id")));
        	
           this.tService.createTransaction(transaction);
            return "redirect:/task";
        }
	}
}
