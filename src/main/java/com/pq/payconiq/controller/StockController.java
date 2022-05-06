package com.pq.payconiq.controller;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pq.payconiq.entity.Stock;
import com.pq.payconiq.exception.StockFoundException;
import com.pq.payconiq.model.StockRequest;
import com.pq.payconiq.service.StockService;

@RestController
@RequestMapping("/api")
public class StockController {

	@Autowired
	private StockService stockService;
	
	/**
	 * Get a list of stocks
	 * 
	 * @param index
	 * @param pageSize
	 * @return Object
	 * @throws Exception
	 */
	@GetMapping("/stocks")
	public ResponseEntity<Object> getAllStocks(@RequestParam(name = "index", defaultValue = "0", required = false) int index,
            @RequestParam(name = "pageSize", defaultValue = "100", required = false) int pageSize) throws Exception {
		return new ResponseEntity<>(stockService.getAllStocks(index, pageSize), HttpStatus.OK);
	}
	
	/**
	 * Create a stock
	 * 
	 * @param stockReqObj
	 * @return Object
	 * @throws Exception
	 */
	@PostMapping("/stocks")
	public ResponseEntity<Object> createStock(@Valid @RequestBody StockRequest stockRequest) throws Exception {
		Stock stock = stockService.createStock(stockRequest);
		if(Objects.isNull(stock)) {
			throw new StockFoundException("Stock is already exist");
		}
		return new ResponseEntity<>(stock, HttpStatus.CREATED);
	}
	
	/**
	 * Get one stock from list 
	 * 
	 * @param id
	 * @return Object
	 * @throws Exception
	 */
	@GetMapping("/stocks/{id}")
	public ResponseEntity<Object> getStock(@PathVariable(name = "id", required = true) int id) throws Exception {
		return new ResponseEntity<>(stockService.getStock(id), HttpStatus.OK);
	}
	
	/**
	 * Update the price of a single stock
	 * 
	 * @param stockRequest
	 * @param id
	 * @return Object
	 * @throws Exception
	 */
	@PatchMapping("/stocks/{id}")
	public ResponseEntity<Object> updateStock(@RequestBody StockRequest stockRequest, 
			@PathVariable(name = "id", required = true) int id) throws Exception {
		Stock stock = stockService.updateStock(stockRequest, id);
		if(Objects.isNull(stock)) {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
		return new ResponseEntity<>(stock, HttpStatus.OK);
	}
	
	/**
	 * Delete a single stock
	 * 
	 * @param id
	 * @throws Exception
	 */
	@DeleteMapping("/stocks/{id}")
	public ResponseEntity<Void> deleteStock(@PathVariable(name = "id", required = true) int id) throws Exception {
		boolean status = stockService.deleteStock(id);
		if(status == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
