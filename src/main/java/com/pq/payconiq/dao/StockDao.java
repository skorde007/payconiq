package com.pq.payconiq.dao;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pq.payconiq.entity.Stock;


public interface StockDao extends JpaRepository<Stock, Integer>{

	@Transactional
	@Query("select s from Stock s")
	public Page<Stock> findAllStocks(Pageable paging);
	
	@Transactional
	@Query("select s from Stock s where s.name= :name")
	public Stock getStockByName(@Param("name") String name);
}
