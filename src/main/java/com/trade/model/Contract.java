package com.trade.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Contract {
	
	public Contract() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Contract(int contractId, String description) {
		super();
		this.contractId = contractId;
		this.description = description;
	}
	public Contract(String description) {
		super();
		this.description = description;
	}
	@Id
	@Column(nullable = false, unique = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long contractId;
	
	@Column
	private String description;
	/**
	 * @return the contractId
	 */
	public long getContractId() {
		return contractId;
	}
	/**
	 * @param contractId the contractId to set
	 */
	public void setContractId(long contractId) {
		this.contractId = contractId;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
