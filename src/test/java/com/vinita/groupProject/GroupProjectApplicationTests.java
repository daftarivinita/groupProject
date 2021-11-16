package com.vinita.groupProject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vinita.groupProject.models.PortfolioBalance;
import com.vinita.groupProject.repositories.PortfolioBalanceRepository;

@SpringBootTest
class GroupProjectApplicationTests {

	@Autowired
	PortfolioBalanceRepository pRepo;

	@Test
	void PortBalanceCreate() {
	PortfolioBalance pBalance = new PortfolioBalance();
	pBalance.setPortfolioAmount(1000d);
	pBalance.setMonth("Nov");
	PortfolioBalance pBalance1 = new PortfolioBalance();
	pBalance1.setPortfolioAmount(2000d);
	pBalance1.setMonth("Dec");
	pRepo.save(pBalance);
	pRepo.save(pBalance1);
	}

}
