package com.pq.payconiq.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pq.payconiq.dao.StockDao;
import com.pq.payconiq.dto.StockDto;
import com.pq.payconiq.entity.Stock;
import com.pq.payconiq.model.StockRequest;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private StockDao stockDao;
	
	private StockDto prepareReportData(Page<Stock> page) throws Exception {
		Long totalElements = page.getTotalElements();
        Integer totalPages = page.getTotalPages();
        List<Stock> stock = page.getContent();

        StockDto response = new StockDto();
        response.setTotalRows(totalElements);
        response.setTotalPages(totalPages);
        response.setData(stock);
        return response;
    }
	
	@Override
	public StockDto getAllStocks(int index, int pageSize) throws Exception {
		Pageable paging = PageRequest.of(index, pageSize);
		Page<Stock> stock = stockDao.findAllStocks(paging);
		return prepareReportData(stock);
	}

	@Override
	public Stock createStock(StockRequest stockRequest) throws Exception {
		if(!Objects.isNull(stockRequest)) {
			Stock stockExist = stockDao.getStockByName(stockRequest.getName());
			if(Objects.isNull(stockExist)) {
				Stock stockObj = new Stock();
				stockObj.setName(stockRequest.getName() != null?stockRequest.getName(): null);
				stockObj.setCurrentPrice(stockRequest.getCurrentPrice() != null?stockRequest.getCurrentPrice(): null);
				java.sql.Timestamp stamp = new java.sql.Timestamp(new Date().getTime());
				stockObj.setLastUpdate(stamp);
				Stock stock = stockDao.save(stockObj);
				if(!Objects.isNull(stock))
					return stock;
			}
		}
		return null;
	}

	@Override
	public Stock getStock(int id) throws Exception {
		Optional<Stock> stock = stockDao.findById(id);
		return stock.get();
	}

	@Override
	public Stock updateStock(StockRequest stockRequest, int id) throws Exception {
		if(!Objects.isNull(stockRequest)) {
			Optional<Stock> stockExist = stockDao.findById(id);
			if(stockExist.isPresent()) {
				Stock stockObj = stockExist.get();
				stockObj.setName(stockRequest.getName() != null?stockRequest.getName(): stockObj.getName());
				stockObj.setCurrentPrice(stockRequest.getCurrentPrice() != null?stockRequest.getCurrentPrice(): null);
				java.sql.Timestamp stamp = new java.sql.Timestamp(new Date().getTime());
				stockObj.setLastUpdate(stamp);
				Stock stock = stockDao.save(stockObj);
				if(!Objects.isNull(stock))
					return stock;
			}
		}
		return null;
	}

	@Override
	public boolean deleteStock(int id) throws Exception {
		Optional<Stock> stockExist = stockDao.findById(id);
		if(stockExist.isPresent()) {
			stockDao.deleteById(stockExist.get().getId());
			return true;
		}
		return false;
	}
}
