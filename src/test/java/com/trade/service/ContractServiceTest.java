package com.trade.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.trade.exception.SLAThresholdBreachedException;
import com.trade.model.Contract;
import com.trade.repository.ContractRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContractServiceTest {
	@Autowired
	ContractService contractService;
	
	@Mock
	ContractRepository contractRepositoryMock;

    @Before
    public void setup() throws SLAThresholdBreachedException {
    	contractService.setContractRepository(contractRepositoryMock);
    	when(contractRepositoryMock.findById(1L)).thenReturn(Optional.of(new Contract(1,"desc1")));
    	when(contractRepositoryMock.findById(2L)).thenReturn(Optional.of(new Contract(2,"desc2")));
    	when(contractRepositoryMock.findById(3L)).thenReturn(Optional.of(new Contract(3,"desc3")));
    	List<Contract> listContract = java.util.Arrays.asList(new Contract(1,"desc1"),new Contract(2,"desc2"),new Contract(3,"desc3"));
    	when(contractRepositoryMock.findAll()).thenReturn(listContract);
    }

    @Test
    public void getAllContractTest(){
    	List<Contract> contractList=contractService.getAllContracts();
    	assertTrue(contractList.size()>=3);
    }

    @Test
    public void getContractByIdTest() {
    	Optional<Contract> contractOptional=contractService.getContractById(1);
    	if(contractOptional.isPresent()) {
        	assertTrue(contractOptional.get().getContractId()==1L);
        	return;
    	}
    	assert false;
    }

    @Test
    public void updateTest() {
    	List<Contract> contractList=contractService.getAllContracts();
    	Optional<Contract> contractOptional=contractService.getContractById(1);
    	Contract contract = contractOptional.get();
    	assertTrue(contract.getDescription().equals("desc1"));
    	contract.setDescription("descNew");
    	contractService.saveOrUpdate(contract);
    	Optional<Contract> contractUpdatedOptional=contractService.getContractById(1);
    	Contract contractUpdated= contractUpdatedOptional.get();
    	assertTrue(contractUpdated.getDescription().equals("descNew"));
    	List<Contract> contractListUpdated=contractService.getAllContracts();
    	assertTrue(contractList.size()==contractListUpdated.size());
    }
}
