package com.vinita.groupProject.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vinita.groupProject.models.Transaction;



@Repository
public interface TransactionRepository  extends CrudRepository<Transaction, Long> {

}
