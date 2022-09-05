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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table
public class Transactions {
	@Id
	private long transaction_id;
	private long account_id;
	private Date transaction_date;
	private long transaction_type_id;
	private Double transaction_amount;
	private String transaction_note;
	private Double balance;

	public long getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(long transaction_id) {
		this.transaction_id = transaction_id;
	}

	public long getAccount_id() {
		return account_id;
	}

	public void setAccount_id(long account_id) {
		this.account_id = account_id;
	}

	public Date getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(Date transaction_date) {
		this.transaction_date = transaction_date;
	}

	public long getTransaction_type_id() {
		return transaction_type_id;
	}

	public void setTransaction_type_id(long transaction_type_id) {
		this.transaction_type_id = transaction_type_id;
	}

	public Double getTransaction_amount() {
		return transaction_amount;
	}

	public void setTransaction_amount(Double transaction_amount) {
		this.transaction_amount = transaction_amount;
	}

	public String getTransaction_note() {
		return transaction_note;
	}

	public void setTransaction_note(String transaction_note) {
		this.transaction_note = transaction_note;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Transactions [transaction_id=" + transaction_id + ", account_id=" + account_id + ", transaction_date="
				+ transaction_date + ", transaction_type_id=" + transaction_type_id + ", transaction_amount="
				+ transaction_amount + ", transaction_note=" + transaction_note + ", balance=" + balance + "]";
	}

	

	

}
