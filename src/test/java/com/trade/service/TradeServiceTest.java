package com.trade.service;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.trade.exception.SLAThresholdBreachedException;
import com.trade.model.Contract;
import com.trade.model.Trade;
import org.junit.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeServiceTest {
	@Autowired
	TradeService tradeService;
	
	@Autowired
	ContractService contractService;

    @Before
    public void setup() throws SLAThresholdBreachedException {
		contractService.saveOrUpdate(new Contract(1,"desc1"));
		contractService.saveOrUpdate(new Contract(2,"desc2"));
		contractService.saveOrUpdate(new Contract(3,"desc3"));
		tradeService.saveOrUpdate(new Trade(1, 100, 100000, new Contract(1,"desc1"), new Date()));
		tradeService.saveOrUpdate(new Trade(2, 200, 200000, new Contract(2,"desc2"), new Date()));
		tradeService.saveOrUpdate(new Trade(3, 300, 300000, new Contract(3,"desc3"), new Date()));
		tradeService.saveOrUpdate(new Trade(4, 400, 400000, null, new Date()));
    }
    @After(value = "")
    public void destroy() {
    	List<Trade> tradeList=tradeService.getAllTrades();
    	for(Trade trade:tradeList) {
    		tradeService.delete(trade.getTradeId());
    	}
    	List<Contract> contractList=contractService.getAllContracts();
    	for(Contract contract:contractList) {
    		contractService.delete(contract.getContractId());
    	}
    }
    
    @Test
    public void getAllTradesTest(){
    	List<Trade> tradeList=tradeService.getAllTrades();
    	assertTrue(tradeList.size()>=4);
    }

    @Test
    public void getTradeByIdTest() {
    	Optional<Trade> tradeOptional=tradeService.getTradeById(1);
    	if(tradeOptional.isPresent()) {
        	assertTrue(tradeOptional.get().getTradeId()==1L);
        	return;
    	}
    	assert false;
    }

    @Test
    public void deleteTest(){
    	tradeService.delete(4);
    	assert tradeService.getTradeById(4).isEmpty();
    }

    @Test
    public void saveOrUpdateTest() throws SLAThresholdBreachedException {
    	List<Trade> tradeList=tradeService.getAllTrades();
		tradeService.saveOrUpdate(new Trade(5, 500, 500000, null, new Date()));
    	List<Trade> tradeListSave=tradeService.getAllTrades();
    	Optional<Trade> tradeOptional=tradeService.getTradeById(5);
    	assert tradeOptional.isPresent();
    	assertTrue(tradeList.size()+1==tradeListSave.size());
    }

    @Test
    public void updateTest() throws SLAThresholdBreachedException {
    	List<Trade> tradeList=tradeService.getAllTrades();
    	Optional<Trade> tradeOptional=tradeService.getTradeById(1);
    	Trade trade = tradeOptional.get();
    	assertTrue(trade.getValue()==100);
    	trade.setValue(500);
    	tradeService.saveOrUpdate(trade);
    	Optional<Trade> tradeUpdatedOptional=tradeService.getTradeById(1);
    	Trade tradeUpdated= tradeUpdatedOptional.get();
    	assertTrue(tradeUpdated.getValue()==500);
    	List<Trade> tradeListUpdated=tradeService.getAllTrades();
    	assertTrue(tradeList.size()==tradeListUpdated.size());
    }

}
