package com.tranction.management.service;

import java.util.Date;
import java.util.Random;

import javax.transaction.Transactional;

import org.aspectj.apache.bcel.classfile.Utility;
import org.hibernate.hql.internal.ast.tree.IsNotNullLogicOperatorNode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tranction.management.dao.CustomerRepo;
import com.tranction.management.dao.TransactionRepo;
import com.tranction.management.dao.TransactionsTypeRepo;
import com.tranction.management.entity.Customers;
import com.tranction.management.entity.Transaction_Types;
import com.tranction.management.entity.Transactions;
import com.tranction.management.utility.TransactionTypes;

@Service
@Transactional
public class BankingTransactionManagementServiceImpl implements BankingTransactionManagementService {
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private TransactionsTypeRepo transactionsTypeRepo;
	@Autowired
	private TransactionRepo transactionRepo;

	@Override
	public Object create(Customers customers) {
		customers.setCustomer_id(generateRandom(10));
		customers.setAccount_id(generateRandom(11));
		customers.setDate_opened(new Date());
		customers.setDate_closed(null);
		Customers newCustomers = customerRepo.save(customers);
		return newCustomers;
	}

	

	@Override
	public Object update(Customers customers, long customerId) throws InActiveAccountException {
		// @SuppressWarnings("deprecation")
		Customers cust = customerRepo.getCustomersById(customerId);
		Customers custUpdate = null;
		if(cust.getDate_closed() != null) {
			throw new InActiveAccountException("Account is closed.");
		}
		else {
		long accId = cust.getAccount_id();
		long custId = cust.getCustomer_id();
		Double ammount = cust.getCurrent_balance();
		Date open_date = cust.getDate_opened();
		//Double min_ammount = cust.getMinimum_balance();
		BeanUtils.copyProperties(customers, cust);
		cust.setAccount_id(accId);
		cust.setCustomer_id(custId);
		cust.setCurrent_balance(ammount);
		cust.setDate_opened(open_date);
		cust.setDate_closed(null);
		//cust.setMinimum_balance(min_ammount);
		custUpdate = customerRepo.save(cust);
		}
		return custUpdate;
	}

	@Override
	public Object credit(long acc_no, double amount) throws InActiveAccountException {
		Customers cust = customerRepo.getCustomersByAccId(acc_no);
		Transactions trans;
		
		if(cust.getDate_closed() != null) {
			throw new InActiveAccountException("Account is closed.");
		}
		else {
		double total_balance=cust.getCurrent_balance() + amount;
		cust.setCurrent_balance(total_balance);
		customerRepo.save(cust);

		Transaction_Types tTypes = new Transaction_Types();
		tTypes.setTransaction_type_id(generateRandom(10));
		tTypes.setTransaction_type(TransactionTypes.CREDIT.toString());
	   //tTypes.setTransaction(transactions);
		Transaction_Types tt = transactionsTypeRepo.save(tTypes);

		Transactions transactions = new Transactions();
		transactions.setTransaction_id(generateRandom(14));
		transactions.setAccount_id(cust.getAccount_id());
		transactions.setTransaction_amount(amount);
		transactions.setBalance(total_balance);
		transactions.setTransaction_date(new java.sql.Date(new Date().getTime()));
		transactions.setTransaction_type_id(tt.getTransaction_type_id());
		transactions.setTransaction_note(amount + TransactionTypes.CREDIT.toString() + " Total Amount for account no: "
				+ acc_no + " is: " + (total_balance));
       
		
		trans = transactionRepo.save(transactions);
		}
		return trans;
	}

	
	  @Override 
	  public Object debit(long acc_no, double amount) throws InActiveAccountException, InSufficientException { 
		    Customers cust = customerRepo.getCustomersByAccId(acc_no);
		    Transactions trans = null;
		    
		    if(cust.getDate_closed() != null) {
		    	throw new InActiveAccountException("Account is closed.");
		    }
		    else if (cust.getCurrent_balance() < amount) {
		    	throw new InSufficientException("Insufficient balance in account");
		    }
		    else {
			double total_balance=cust.getCurrent_balance() - amount;
			cust.setCurrent_balance(total_balance);
			customerRepo.save(cust);

			Transaction_Types tTypes = new Transaction_Types();
			tTypes.setTransaction_type_id(generateRandom(10));
			tTypes.setTransaction_type(TransactionTypes.DEBIT.toString());
		   //tTypes.setTransaction(transactions);
			Transaction_Types tt = transactionsTypeRepo.save(tTypes);

			Transactions transactions = new Transactions();
			transactions.setTransaction_id(generateRandom(14));
			transactions.setAccount_id(cust.getAccount_id());
			transactions.setTransaction_amount(amount);
			transactions.setBalance(total_balance);
			transactions.setTransaction_date(new java.sql.Date(new Date().getTime()));
			transactions.setTransaction_type_id(tt.getTransaction_type_id());
			transactions.setTransaction_note(amount + TransactionTypes.DEBIT.toString() + " Total Amount for account no: "
					+ acc_no + " is: " + (total_balance));
	       
			
			trans = transactionRepo.save(transactions);
		    }
			return trans;
	  }
	 

	@Override
	public Object close(long acc_no) {
		// TODO Auto-generated method stub
		Customers cust = customerRepo.getCustomersByAccId(acc_no);
		cust.setDate_closed(new Date());
		customerRepo.save(cust);
		return cust;
	}

	@Override
	public Object balance(long acc_no) throws InActiveAccountException {
		// TODO Auto-generated method stub
		Customers cust = customerRepo.getCustomersByAccId(acc_no);
		double total_balance = 0;
		
		if(cust.getDate_closed() != null) {
	    	throw new InActiveAccountException("Account is closed.");
	    }else {
	    	total_balance = cust.getCurrent_balance();
	    }
		
		return total_balance;
	}
	
	public static long generateRandom(int length) {
		Random random = new Random();
		char[] digits = new char[length];
		digits[0] = (char) (random.nextInt(9) + '1');
		for (int i = 1; i < length; i++) {
			digits[i] = (char) (random.nextInt(10) + '0');
		}
		return Long.parseLong(new String(digits));
	}
}
