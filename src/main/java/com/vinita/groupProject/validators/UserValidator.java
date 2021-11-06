package com.vinita.groupProject.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.vinita.groupProject.models.User;
import com.vinita.groupProject.repositories.UserRepository;



@Component
public class UserValidator {
	@Autowired
	private UserRepository uRepo;
	

	 // 1
    
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
    
    // 2
   
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            // 3
            errors.rejectValue("password", "Match", "do not matcb!!!!!!!!!!!!!!");
        }
        if(this.uRepo.existsByEmail(user.getEmail())) {
        	errors.rejectValue("email", "unique", "email has already benn taken");
        }
    }

	
}
