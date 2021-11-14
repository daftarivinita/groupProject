package com.vinita.groupProject.models;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import javax.persistence.OneToMany;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Size(max= 15)
    private String firstName;
    
    @NotBlank
    @Size(max= 15)
    private String lastName;
    
    @Email
    @NotBlank
    private String email;
    
    private String password;
    
    @Transient
    private String confirmPassword;
    
    @Column(updatable=false)
    private Date createdAt;
    
    private Date updatedAt;
    
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY )
    private List<PortfolioBalance> allBalance;
    
      
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Transaction> allTransactions;
    
    

    @ElementCollection
    @CollectionTable(name = "user_portfolio_count", 
      joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "currency_id")
    @Column(name = "count")
	Map<Currency, Double> currencies = new HashMap<>();

	public User() {
		
    }
    

    
    public User(@NotBlank @Size(max = 15) String firstName, @NotBlank @Size(max = 15) String lastName,
			@Email @NotBlank String email, String password, String confirmPassword) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}



	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

/// all getter and setter

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getConfirmPassword() {
		return confirmPassword;
	}



	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}



	public Date getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}



	public Date getUpdatedAt() {
		return updatedAt;
	}



	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	


	public List<Transaction> getAllTransactions() {
		return allTransactions;
	}
	
	
	
	public void setAllTransactions(List<Transaction> allTransactions) {
		this.allTransactions = allTransactions;
	}



	public Map<Currency, Double> getCurrencies() {
		return currencies;
	}



	public void setCurrencies(Map<Currency, Double> currencies) {
		this.currencies = currencies;
	}



	public List<PortfolioBalance> getAllBalance() {
		return allBalance;
	}



	public void setAllBalance(List<PortfolioBalance> allBalance) {
		this.allBalance = allBalance;
	}


}
