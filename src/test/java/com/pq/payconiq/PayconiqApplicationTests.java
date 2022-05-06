package com.pq.payconiq;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pq.payconiq.dto.StockDto;
import com.pq.payconiq.entity.Stock;
import com.pq.payconiq.model.StockRequest;
import com.pq.payconiq.service.StockService;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class PayconiqApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private StockService stockService;

	@Test
	public void testGetAllStocks() throws Exception {
		java.sql.Timestamp stamp = new java.sql.Timestamp(new Date().getTime());
		List<Stock> stockList = List.of(new Stock(1,"TV", new BigDecimal("552209"), stamp),
				new Stock(2,"Mobile", new BigDecimal("122207"), stamp));
		
		StockDto stockDto = new StockDto();
		stockDto.setTotalRows(0L);
		stockDto.setTotalPages(1);
		stockDto.setData(stockList);
        
		Mockito.when(stockService.getAllStocks(Mockito.any(Integer.class), Mockito.any(Integer.class))).thenReturn(stockDto);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/stocks")
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testCreateStock() throws Exception {
		java.sql.Timestamp stamp = new java.sql.Timestamp(new Date().getTime());
		StockRequest stockRequest = new StockRequest("Laptop", new BigDecimal("7500.00"));
		stockRequest.setLastUpdate(stamp);
		Stock stock = new Stock(3,"Laptop", new BigDecimal("7500.00"), stamp);
		
		Mockito.when(stockService.createStock(Mockito.any(StockRequest.class))).thenReturn(stock);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/stocks")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new ObjectMapper().writeValueAsString(stockRequest)))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		
		HashMap<String, String> map = new HashMap<String, String>();
        JSONObject jObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        Iterator<?> keys = jObject.keys();

        while( keys.hasNext() ){
            String key = (String)keys.next();
            String value = jObject.getString(key); 
            map.put(key, value);
        }

        if(status == HttpStatus.FOUND.value()) {
			Assert.assertEquals(map.get("message"), "Stock is already exist");
		} else {
			assertTrue(map.size() > 0);
			Assert.assertEquals(Integer.parseInt(map.get("id")), stock.getId());
		}
	}

	@Test
	public void testGetStock() throws Exception {
		int id = 1;
		java.sql.Timestamp stamp = new java.sql.Timestamp(new Date().getTime());
		Stock stock = new Stock(1,"TV", new BigDecimal("552209"), stamp);
		
		Mockito.when(stockService.getStock(Mockito.any(Integer.class))).thenReturn(stock);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/stocks/"+id)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testUpdateStock() throws Exception {
		int caseId1 = 1;
		int caseId2 = 4;
		java.sql.Timestamp stamp = new java.sql.Timestamp(new Date().getTime());
		StockRequest stockRequest = new StockRequest("Laptop", new BigDecimal("6400.00"));
		stockRequest.setLastUpdate(stamp);
		Stock stock = new Stock(1,"Laptop", new BigDecimal("6400.00"), stamp);
		
		Mockito.when(stockService.updateStock(Mockito.any(StockRequest.class), Mockito.any(Integer.class))).thenReturn(stock);
		mockMvc.perform(MockMvcRequestBuilders.patch("/api/stocks/"+caseId1)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new ObjectMapper().writeValueAsString(stockRequest)))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		Mockito.when(stockService.updateStock(Mockito.any(StockRequest.class), Mockito.any(Integer.class))).thenReturn(stock);
		mockMvc.perform(MockMvcRequestBuilders.patch("/api/stocks/"+caseId2)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new ObjectMapper().writeValueAsString(stockRequest)))
				.andExpect(MockMvcResultMatchers.status().isNotModified());
	}
	
	@Test
	public void testDeleteStock() throws Exception {
		int senario = 2;
		
		Mockito.when(stockService.deleteStock(Mockito.any(Integer.class))).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/stocks/"+senario)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		Mockito.when(stockService.deleteStock(Mockito.any(Integer.class))).thenReturn(false);
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/stocks/"+senario)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
}
