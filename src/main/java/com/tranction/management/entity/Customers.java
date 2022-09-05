package com.tranction.management.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
@Entity
@IdClass(TransctionIds.class)
public class Customers {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long customer_id;
	private String customer_name;
	@Pattern(regexp = "(0/91)?[6-9][0-9]{9}")
	private String customer_phone;
	@Column(unique=true)
	@Email
	private String customer_email;
	@Column(name = "opened_date")
	private Date date_opened;
	@Column(name = "closed_date")
	private Date date_closed;
	private String accounts="";
	@Id
	private long account_id;
	private Double minimum_balance;
	private Double current_balance;

	public long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	

	public String getCustomer_phone() {
		return customer_phone;
	}

	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}

	public String getCustomer_email() {
		return customer_email;
	}

	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}

	public Date getDate_opened() {
		return date_opened;
	}

	public void setDate_opened(Date date_opened) {
		this.date_opened = date_opened;
	}

	public Date getDate_closed() {
		return date_closed;
	}

	public void setDate_closed(Date date_closed) {
		this.date_closed = date_closed;
	}

	public String getAccounts() {
		return accounts;
	}

	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}

	public long getAccount_id() {
		return account_id;
	}

	public void setAccount_id(long account_id) {
		this.account_id = account_id;
	}

	public Double getMinimum_balance() {
		return minimum_balance;
	}

	public void setMinimum_balance(Double minimum_balance) {
		this.minimum_balance = minimum_balance;
	}

	public Double getCurrent_balance() {
		return current_balance;
	}

	public void setCurrent_balance(Double current_balance) {
		this.current_balance = current_balance;
	}

	@Override
	public String toString() {
		return "Transaction_Types [customer_id=" + customer_id + ", customer_name=" + customer_name + ", customer_hone="
				+ customer_phone + ", customer_email=" + customer_email + ", date_opened=" + date_opened
				+ ", date_closed=" + date_closed + ", accounts=" + accounts + ", account_id=" + account_id
				+ ", minimum_balance=" + minimum_balance + ", current_balance=" + current_balance + "]";
	}


}
