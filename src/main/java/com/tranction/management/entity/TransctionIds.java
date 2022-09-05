package com.tranction.management.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TransctionIds implements Serializable {

	/**
	 * 
	 */
	private static final Long serialVersionUID = 1L;
	
	private long customer_id;
	private long account_id;

	
	public TransctionIds() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}

	public long getAccount_id() {
		return account_id;
	}

	public void setAccount_id(long account_id) {
		this.account_id = account_id;
	}

	@Override
	public String toString() {
		return "TransctionIds [customer_id=" + customer_id + ", account_id=" + account_id + "]";
	}

}
