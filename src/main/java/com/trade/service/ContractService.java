package com.trade.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.trade.model.Contract;
import com.trade.repository.ContractRepository;

@Service
public class ContractService {
	@Autowired
	ContractRepository contractRepository;

	/**
	 * @param contractRepository the contractRepository to set
	 */
	public void setContractRepository(ContractRepository contractRepository) {
		this.contractRepository = contractRepository;
	}

	public List<Contract> getAllContracts() {
		final List<Contract> contractList = new ArrayList<Contract>();
		contractRepository.findAll().forEach(contract -> contractList.add(contract));
		return contractList;
	}

	public Optional<Contract> getContractById(long id) {
		return contractRepository.findById(id);
	}

	public void saveOrUpdate(Contract contract) {
		contractRepository.save(contract);
	}

	public void delete(long id) {
		contractRepository.deleteById(id);
	}

	public void update(Contract contract) {
		contractRepository.save(contract);
	}

}