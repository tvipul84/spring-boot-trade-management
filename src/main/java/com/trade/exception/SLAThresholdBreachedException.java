package com.trade.exception;

public class SLAThresholdBreachedException extends Exception {
	public  SLAThresholdBreachedException() {
		super();
	}
	public SLAThresholdBreachedException(String msg) {
		super(msg);
	}
}
