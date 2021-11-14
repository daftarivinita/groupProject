package com.vinita.groupProject.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vinita.groupProject.models.PortfolioBalance;

@Repository
public interface PortfolioBalanceRepository extends CrudRepository<PortfolioBalance, Long> {
	
	List<PortfolioBalance> findAll();

	PortfolioBalance save(Double balance);
	

}
