package com.vinita.groupProject.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.vinita.groupProject.models.User;
import com.vinita.groupProject.repositories.UserRepository;



@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	public CurrencyService cService;
	@Autowired
	public PortfolioService pService;
    
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
    
}
