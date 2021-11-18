package com.vinita.groupProject.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vinita.groupProject.models.Piechart;

@Repository
public interface PiechartRepository extends CrudRepository<Piechart, Long> {
	List<Piechart> findAll();

}
