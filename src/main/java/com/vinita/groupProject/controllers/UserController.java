package com.vinita.groupProject.controllers;



import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vinita.groupProject.models.Currency;
import com.vinita.groupProject.models.User;
import com.vinita.groupProject.services.CurrencyService;
import com.vinita.groupProject.services.TransactionService;
import com.vinita.groupProject.services.UserService;
import com.vinita.groupProject.validators.UserValidator;






@Controller
public class UserController {
	@Autowired
	private UserService uService;
	@Autowired
	private UserValidator validator;
	@Autowired
	public CurrencyService cService;
	@Autowired
	public TransactionService tService;
	
	
	@GetMapping("/")
	public String landing(@ModelAttribute("user") User user) {
		return "user.jsp";
		
	
	}
	@PostMapping("/loginToRegister")
	public String createUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		validator.validate(user, result);
		if (result.hasErrors()) {
			return "user.jsp";
		} else {
			List<Currency> allCurency = this.cService.getAllCurrency();
			for(Currency c:allCurency) {
				if(c.getName().equals("USD")) {
					user.getCurrencies().put(c, 11000d);
				}else {
					user.getCurrencies().put(c, 0d);
				}
			}
			User newUser = this.uService.registerUser(user);
			session.setAttribute("user__id",newUser.getId());
			return "redirect:/dashboard";
		}
	}
				
			
	
	@PostMapping("/login")
	public String login(HttpSession session, @RequestParam("lemail") String email, @RequestParam("lpassword") String password, RedirectAttributes redirectAttribute) {
		if(!this.uService.authenticateUser(email, password)) {
			redirectAttribute.addFlashAttribute("loginError", "Invalid Credentials");
			return "redirect:/";
		}
		User userToLog = this.uService.getUserByEmail(email);
		session.setAttribute("user__id", userToLog.getId());
		
		return "redirect:/dashboard";
	}
	
	
	//tologout
	@GetMapping("/logout")
	public String logoutUser(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}
