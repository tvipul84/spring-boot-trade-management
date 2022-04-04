package com.trade.repository;

import org.springframework.data.repository.CrudRepository;
import com.trade.model.Contract;

public interface ContractRepository extends CrudRepository<Contract, Long> {
}
