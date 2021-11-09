package com.vinita.groupProject.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vinita.groupProject.models.Portfolio;



@Repository
public interface PortfolioRepository  extends CrudRepository<Portfolio, Long> {

}
