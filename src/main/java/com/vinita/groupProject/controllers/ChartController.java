package com.vinita.groupProject.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vinita.groupProject.models.Piechart;
import com.vinita.groupProject.models.PortfolioBalance;
import com.vinita.groupProject.models.User;
import com.vinita.groupProject.services.PiechartService;
import com.vinita.groupProject.services.PortfolioBalanceService;
import com.vinita.groupProject.services.UserService;

@Controller
public class ChartController {

	@Autowired
	private UserService uService;

	@Autowired
	private PortfolioBalanceService pService;
	
	@Autowired
	private PiechartService pieService;

	@RequestMapping("/chart")
	public String showChart() {
		return "chart.jsp";
	}

	/*
	 * 
	 */

//	@RequestMapping("/linechartdata")
//	@ResponseBody
//		public String getDataFromDB() {
//		List<Chart> dataList = cService.findAll();
//		JsonArray jsonMonth = new JsonArray();
//		JsonArray jsonPortBalance = new JsonArray();
//		JsonObject json = new JsonObject();
//		dataList.forEach(data->{
//			jsonMonth.add(data.getMonth());
//			jsonPortBalance.add(data.getPorfolioAmount());
//		});
//		json.add("Month",jsonMonth);
//		json.add("PortfolioBalance", jsonPortBalance);
//		return json.toString();			
//		}

	@RequestMapping("/linechartdata")
	@ResponseBody
	public String getData(HttpSession session) {
		User user = this.uService.findUserById((Long) session.getAttribute("user__id"));
		List<PortfolioBalance> dataList = pService.getAllBalance();
		JsonArray jsonMonth = new JsonArray();
		JsonArray jsonBalance = new JsonArray();
		JsonObject json = new JsonObject();
		dataList.forEach(data->{
			jsonMonth.add(data.getMonth());
			jsonBalance.add(data.getPortfolioAmount());
		});
		json.add("Month",jsonMonth);
		json.add("PortfolioBalance", jsonBalance);
		return json.toString();		

	}
	
	@RequestMapping("/piechart")
	public String showPieChart() {
		return "pieChart.jsp";
	}
	
	@RequestMapping("/piechartdata")
	public ResponseEntity<?> getPieChartData() {
		List<Piechart> pieListData = pieService.getAll();
		return new ResponseEntity<>(pieListData, HttpStatus.OK);		
		
	}

}
