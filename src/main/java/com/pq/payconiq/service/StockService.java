package com.pq.payconiq.service;

import com.pq.payconiq.dto.StockDto;
import com.pq.payconiq.entity.Stock;
import com.pq.payconiq.model.StockRequest;

public interface StockService {

	public StockDto getAllStocks(int index, int pageSize) throws Exception;
	public Stock createStock(StockRequest stockRequest) throws Exception;
	public Stock getStock(int id) throws Exception;
	public Stock updateStock(StockRequest stockRequest, int id) throws Exception;
	public boolean deleteStock(int id) throws Exception;
}
