package com.tranction.management.entity;


import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table
public class Transaction_Types {
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private long transaction_type_id;
	private String transaction_type;
	
	/*
	 * @OneToOne(mappedBy = "Transaction_Types",cascade = CascadeType.ALL)
	 * //@PrimaryKeyJoinColumn private Transactions transaction;
	 */

	

	public Transaction_Types() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getTransaction_type_id() {
		return transaction_type_id;
	}
	public void setTransaction_type_id(long transaction_type_id) {
		this.transaction_type_id = transaction_type_id;
	}

	public String getTransaction_type() {
		return transaction_type;
	}

	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}

	/*
	 * public Transactions getTransaction() { return transaction; }
	 * 
	 * public void setTransaction(Transactions transaction) { this.transaction =
	 * transaction; }
	 */

	@Override
	public String toString() {
		return "Transaction_Types [transaction_type_id=" + transaction_type_id + ", transaction_type="
				+ transaction_type + "]";
	}

	

}
