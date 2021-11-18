package com.vinita.groupProject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vinita.groupProject.models.Piechart;
import com.vinita.groupProject.models.PortfolioBalance;
import com.vinita.groupProject.repositories.PiechartRepository;
import com.vinita.groupProject.repositories.PortfolioBalanceRepository;

@SpringBootTest
class GroupProjectApplicationTests {

	@Autowired
	PortfolioBalanceRepository pRepo;
	
	@Autowired
	PiechartRepository pieRepo;

	@Test
	void PortBalanceCreate() {
	PortfolioBalance pBalance = new PortfolioBalance();
	pBalance.setPortfolioAmount(1000d);
	pBalance.setMonth("Sep");
	PortfolioBalance pBalance1 = new PortfolioBalance();
	pBalance1.setPortfolioAmount(2000d);
	pBalance1.setMonth("Oct");
	PortfolioBalance pBalance2 = new PortfolioBalance();
	pBalance2.setPortfolioAmount(1500d);
	pBalance2.setMonth("Nov");
	pRepo.save(pBalance);
	pRepo.save(pBalance1);
	pRepo.save(pBalance2);
	}
	
	
	@Test
	void CreatePieChart() {
		Piechart pieChart = new Piechart();
		pieChart.setInvestment(3000d);
		pieChart.setStock("Amazon");
		pieRepo.save(pieChart);
		Piechart pieChart2 = new Piechart();
		pieChart2.setInvestment(5000d);
		pieChart2.setStock("Tesla");
		pieRepo.save(pieChart2);
		Piechart pieChart3 = new Piechart();
		pieChart3.setInvestment(5000d);
		pieChart3.setStock("Cash");
		pieRepo.save(pieChart3);
		Piechart pieChart4 = new Piechart();
		pieChart4.setInvestment(2000d);
		pieChart4.setStock("Google");
		pieRepo.save(pieChart4);
	}

}
