package com.trade.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trade.exception.SLAThresholdBreachedException;
import com.trade.model.AppPropertyConfig;
import com.trade.model.Trade;
import com.trade.repository.TradeRepository;

@Service
public class TradeService {
	@Autowired
	private TradeRepository tradeRepository;
	
	@Autowired
	private AppPropertyConfig appPropertyConfig;

	public List<Trade> getAllTrades() {
		final List<Trade> tradeList = new ArrayList<Trade>();
		tradeRepository.findAll().forEach(trade -> tradeList.add(trade));
		return tradeList;
	}

	public Optional<Trade> getTradeById(long id) {
		return tradeRepository.findById(id);
	}

	public void saveOrUpdate(Trade trade) throws SLAThresholdBreachedException {
		long specifiedAmountOfTime = appPropertyConfig.getSpecifiedAmountOfTime();
		if(trade.getSla()>specifiedAmountOfTime) {
			throw new SLAThresholdBreachedException("SLA must be less than "+specifiedAmountOfTime+" threshold limit.");
		};
		tradeRepository.save(trade);
	}

	public void delete(long id) {
		tradeRepository.deleteById(id);
	}

	public void update(Trade trade) throws SLAThresholdBreachedException {
		long specifiedAmountOfTime = appPropertyConfig.getSpecifiedAmountOfTime();
		if(trade.getSla()>specifiedAmountOfTime) {
			throw new SLAThresholdBreachedException("SLA must be less than "+specifiedAmountOfTime+" threshold limit.");
		};
		tradeRepository.save(trade);
	}
}