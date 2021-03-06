package com.vinita.groupProject.repositories;



import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.vinita.groupProject.models.Currency;

@Repository
public interface CurrencyRepository  extends CrudRepository<Currency, Long> {
	List<Currency> findAll();
	Currency findByName(String name);

}