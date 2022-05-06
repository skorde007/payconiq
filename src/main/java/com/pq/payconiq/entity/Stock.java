package com.pq.payconiq.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "stock")
public class Stock {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	@NotNull(message = "name is mandatory")
	private String name;
	
	@Column(name = "current_price")
	@NotNull(message = "currentPrice is mandatory")
	private BigDecimal currentPrice;
	
	@Column(name = "last_update")
	private Timestamp lastUpdate;

	public Stock() {}
	
	public Stock(int id, String name, BigDecimal currentPrice, Timestamp lastUpdate) {
		this.id = id;
		this.name = name;
		this.currentPrice = currentPrice;
		this.lastUpdate = lastUpdate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}
