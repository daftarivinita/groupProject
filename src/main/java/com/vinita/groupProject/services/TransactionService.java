package com.vinita.groupProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinita.groupProject.models.Transaction;
import com.vinita.groupProject.repositories.TransactionRepository;



@Service
public class TransactionService {
	@Autowired
	private TransactionRepository tRepo;

	//create a Transaction
	public Transaction createTransaction(Transaction transaction) {
		return this.tRepo.save(transaction);
	}
	
	//retrieve a Transaction
	public Transaction getTransaction(Long id) {
		return this.tRepo.findById(id).orElse(null);
	}
	//update a Transaction
	public Transaction updateTransaction(Transaction transaction) {
		return this.tRepo.save(transaction);
	}
	
	//delete a Transaction
	public void destroy(Long id) {
		this.tRepo.deleteById(id);
	}
}
	
	
	
	
		
