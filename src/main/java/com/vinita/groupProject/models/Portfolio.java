package com.vinita.groupProject.models;



import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name="portfolio")
public class Portfolio {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	//private double cash = 11000;
	
	@OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
	private User user;
	
	@ElementCollection
    @CollectionTable(name = "portfolio_currency_count", 
      joinColumns = {@JoinColumn(name = "portfolio_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "currency_id")
    @Column(name = "count")
	Map<Currency, Integer> holdings = new HashMap<>();
	
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

	public Map<Currency, Integer> getHoldings() {
		return holdings;
	}

	public void setHoldings(Map<Currency, Integer> holdings) {
		this.holdings = holdings;
	}

	
}
