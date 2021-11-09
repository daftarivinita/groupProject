package com.vinita.groupProject.models;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="portfolios")
public class Portfolio {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private double cash = 11000;
	
	@OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
	private User user;
	
	@OneToMany(mappedBy="portfolio", fetch = FetchType.LAZY)
	private List<Currency> currency;
	
	
	//constructor
	public Portfolio() {
	}
	

	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public double getCash() {
		return cash;
	}
	public void setCash(double cash) {
		this.cash = cash;
	}
	public List<Currency> getCurrency() {
		return currency;
	}
	public void setCurrency(List<Currency> currency) {
		this.currency = currency;
	}
	
	
	
}
