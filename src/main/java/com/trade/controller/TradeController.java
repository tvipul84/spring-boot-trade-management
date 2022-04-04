package com.trade.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.trade.exception.SLAThresholdBreachedException;
import com.trade.model.Contract;
import com.trade.model.Trade;
import com.trade.service.ContractService;
import com.trade.service.TradeService;

@RestController
public class TradeController {
	@Autowired
	TradeService tradeService;
	
	@Autowired
	ContractService contractService;
	

	@GetMapping("/populateDefaultTrades")
	private List<Trade> getAndPopulateDefaultTrades() throws SLAThresholdBreachedException{
		contractService.saveOrUpdate(new Contract(1,"desc1"));
		contractService.saveOrUpdate(new Contract(2,"desc2"));
		contractService.saveOrUpdate(new Contract(3,"desc3"));
		tradeService.saveOrUpdate(new Trade(1, 100, 100000, new Contract(1,"desc1"), new Date()));
		tradeService.saveOrUpdate(new Trade(2, 200, 200000, new Contract(2,"desc2"), new Date()));
		tradeService.saveOrUpdate(new Trade(3, 300, 300000, new Contract(3,"desc3"), new Date()));
		tradeService.saveOrUpdate(new Trade(4, 400, 400000, null, new Date()));
		return tradeService.getAllTrades();
	}

	@GetMapping("/trades")
	private List<Trade> getAllTrades() {
		return tradeService.getAllTrades();
	}
	@GetMapping("/contracts")
	private List<Contract> getAllContracts() {
		return contractService.getAllContracts();
	}
	
	@GetMapping("/trade/{tradeId}")
	private Trade getTrade(@PathVariable("tradeId") long tradeId) {
		return tradeService.getTradeById(tradeId).get();
	}

	@GetMapping("/contract/{contractId}")
	private Contract getContract(@PathVariable("contractId") long contractId) {
		return contractService.getContractById(contractId).get();
	}
	
	@DeleteMapping("/trade/{tradeId}")
	private void deleteTrade(@PathVariable("tradeId") long tradeId) {
		tradeService.delete(tradeId);
	}

	@DeleteMapping("/contract/{contractId}")
	private void deleteContract(@PathVariable("contractId") long contractId) {
		tradeService.delete(contractId);
	}
	
	@PostMapping("/saveTrade")
	private long saveTrade(@RequestBody Trade trade)  throws SLAThresholdBreachedException{
		if(trade.getContract()!=null) {
			contractService.saveOrUpdate(trade.getContract());
		}
		tradeService.saveOrUpdate(trade);
		return trade.getTradeId();
	}

	@PostMapping("/saveContract")
	private long saveContract(@RequestBody Contract contract) {
		contractService.saveOrUpdate(contract);
		return contract.getContractId();
	}

	@PutMapping("/updateTrade")
	private Trade update(@RequestBody Trade trade)  throws SLAThresholdBreachedException{
		tradeService.saveOrUpdate(trade);
		return trade;
	}

	@PutMapping("/updateContract")
	private Contract update(@RequestBody Contract contract) {
		contractService.saveOrUpdate(contract);
		return contract;
	}

}
