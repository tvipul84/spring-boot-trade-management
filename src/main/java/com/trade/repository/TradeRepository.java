package com.trade.repository;

import org.springframework.data.repository.CrudRepository;

import com.trade.model.Trade;

public interface TradeRepository extends CrudRepository<Trade, Long> {

}
