package com.trade.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Trade {
	
	public Trade() {
		super();
	}
	public Trade(long tradeId, double value, long sla, Contract contract, Date createDate) {
		super();
		this.tradeId = tradeId;
		this.value = value;
		this.sla = sla;
		this.contract = contract;
		this.createDate = createDate;
	}
	public Trade(double value, long sla, Contract contract, Date createDate) {
		super();
		this.value = value;
		this.sla = sla;
		this.contract = contract;
		this.createDate = createDate;
	}
	@Id
	@Column(nullable = false, unique = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long tradeId;
	
	@Column(nullable = false)
	private double value;
	
	@Column(nullable = false)
	private long sla;
	
	//@Column
	@OneToOne
	private Contract contract;
	
	@Column(nullable = false)
	private Date createDate;
	/**
	 * @return the tradeId
	 */
	public long getTradeId() {
		return tradeId;
	}
	/**
	 * @param tradeId the tradeId to set
	 */
	public void setTradeId(long tradeId) {
		this.tradeId = tradeId;
	}
	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(double value) {
		this.value = value;
	}
	/**
	 * @return the sla
	 */
	public long getSla() {
		return sla;
	}
	/**
	 * @param sla the sla to set
	 */
	public void setSla(long sla) {
		this.sla = sla;
	}
	/**
	 * @return the contract
	 */
	@ManyToOne
	@JoinColumn(name="contractId")
	public Contract getContract() {
		return contract;
	}
	/**
	 * @param contract the contract to set
	 */
	public void setContract(Contract contract) {
		this.contract = contract;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
