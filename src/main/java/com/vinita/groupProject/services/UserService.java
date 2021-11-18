package com.vinita.groupProject.services;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vinita.groupProject.models.Currency;
import com.vinita.groupProject.models.User;
import com.vinita.groupProject.repositories.UserRepository;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;


@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	public CurrencyService cService;
	
	
	String url ="https://api.polygon.io/v2/aggs/ticker/";
	String key ="/prev?adjusted=true&apiKey=Il4TXhi_rLZBjI3VC0zSFRmrBhhx3wsD"; 
	
	
	//get all user
	public List<User> getAllUser(){
		return this.userRepository.findAll();
	}
	
	// register user and hash their password
	public User registerUser(User user) {
		//generate the hash
		String hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		//set the user password
		user.setPassword(hash);
		return userRepository.save(user);
	}
	
	// find user by email
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	// find user by id
	public User findUserById(Long id) {
		return this.userRepository.findById(id).orElse(null);
	}
	
	// authenticate user
	public boolean authenticateUser(String email, String password) {
		// first find the user by email
		User user = userRepository.findByEmail(email);
		// if we can't find it by email, return false
		if(user == null) {
			return false;
		} else {
			// if the passwords match, return true, else, return false
			if(BCrypt.checkpw(password, user.getPassword())) {
				return true;
			} else {
				return false;
			}
		}
	}
	public User getUserByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}
	/// getting price of specific stock amzon
	public Double amazonPrice() {
//		Unirest.config().verifySsl(false);
//		HttpResponse<JsonNode> response = Unirest.get(url +"AMZN" + key).asJson();
//		String resultFromApi = response.getBody().getObject().getJSONArray("results").getJSONObject(0).getString("c");
//		double closePrice =Double.parseDouble(resultFromApi);
		double closePrice =3000d;
		return closePrice;
	}
	/// getting price of specific stock Tesla	
	public Double tslaPrice() {
//		Unirest.config().verifySsl(false);
//		HttpResponse<JsonNode> response = Unirest.get(url + "TSLA" + key).asJson();
//		String resultFromApi = response.getBody().getObject().getJSONArray("results").getJSONObject(0).getString("c");
//		double closePrice =Double.parseDouble(resultFromApi);
		double closePrice =1000d;
		return closePrice;
	}	
	
	/// getting price of any stock  and this is more efficient way
	///if this method works for everyone then we can delete above two methods
	public Double currencyPrice(String name) {
		Unirest.config().verifySsl(false);
		HttpResponse<JsonNode> response = Unirest.get(url + name + key).asJson();
		String resultFromApi = response.getBody().getObject().getJSONArray("results").getJSONObject(0).getString("c");
		double closePrice =Double.parseDouble(resultFromApi);
		return closePrice;
	}
	
	
	///we may have to change keyname, according to one own naming convention. 
	///I am making currency name USD, Amazon, Tesla
	public Double getPortfolioBalance(User user) {
		Map<Currency, Double> userBalance = user.getCurrencies();
		Set<Currency> keys = userBalance.keySet();
		Double balance = 0d;
		for(Currency key : keys) {
			if(key.getName().toLowerCase().equals("usd")) {
				balance += userBalance.get(key);
			}
			if(key.getName().toLowerCase().equals("amazon")) {
				balance += userBalance.get(key) * amazonPrice();
			}
			if(key.getName().toLowerCase().equals("tesla")) {
				balance += userBalance.get(key) * tslaPrice();
			}
		}
		return balance;
	}
}






