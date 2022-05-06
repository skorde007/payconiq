package com.pq.payconiq.dto;

import java.util.List;

import com.pq.payconiq.entity.Stock;

public class StockDto {

	private Long totalRows;
	private Integer totalPages;
	private List<Stock> data;
	
	public Long getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(Long totalRows) {
		this.totalRows = totalRows;
	}
	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	public List<Stock> getData() {
		return data;
	}
	public void setData(List<Stock> data) {
		this.data = data;
	}
}
