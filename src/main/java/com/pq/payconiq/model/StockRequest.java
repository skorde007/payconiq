package com.pq.payconiq.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StockRequest {

	@JsonIgnore
	private int id;
	private String name;
	private BigDecimal currentPrice;
	@JsonIgnore
	private Timestamp lastUpdate;

	public StockRequest() {}
	
	public StockRequest(int id, String name, BigDecimal currentPrice, Timestamp lastUpdate) {
		this.id = id;
		this.name = name;
		this.currentPrice = currentPrice;
		this.lastUpdate = lastUpdate;
	}
	
	public StockRequest(String name, BigDecimal currentPrice) {
		this.name = name;
		this.currentPrice = currentPrice;
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
