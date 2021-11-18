package com.vinita.groupProject.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vinita.groupProject.models.PieChart;

@Repository
public interface PieChartRepository extends CrudRepository<PieChart, Long> {
	
	List<PieChart>findAll();

}
