package com.tranction.management.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.google.gson.Gson;
import com.tranction.management.dao.CustomerRepo;
import com.tranction.management.entity.Amount;
import com.tranction.management.entity.Customers;
import com.tranction.management.entity.Transactions;
import com.tranction.management.service.BankingTransactionManagementService;
import com.tranction.management.service.InActiveAccountException;
import com.tranction.management.service.InSufficientException;

@RestController
public class BankingManagementController {
	@Autowired
	private BankingTransactionManagementService bankingService;
	@Autowired
	private CustomerRepo customerRepo;

	@PostMapping("/customer/account")
	public ResponseEntity<Customers> createAccount(@Valid @RequestBody Customers customers) {

		Customers newCustomers = (Customers) bankingService.create(customers);
		return ResponseEntity.ok(newCustomers);
	}

	@PutMapping("/customer/customerid/{customerId}")
	public ResponseEntity<Customers> updateAccount(@Valid @RequestBody Customers customers,
			@PathVariable("customerId") long customerId) {

		System.out.println(customerId);
		Customers newCustomers = null;

		try {
			newCustomers = (Customers) bankingService.update(customers, customerId);
		} catch (InActiveAccountException e) {
			// TODO: handle exception
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
		System.out.println(customerId);
		return ResponseEntity.ok(newCustomers);
	}


	@PutMapping("/credit/customer/accountid/{acc_no}") 
	public ResponseEntity<Transactions> credit(@RequestBody Amount creditAmount, @PathVariable("acc_no") long acc_no) {

		Transactions newCustomers = null;
		System.out.println(creditAmount.getCredit_amount());
		try {
			newCustomers = (Transactions)bankingService.credit(acc_no, creditAmount.getCredit_amount());
		} catch (InActiveAccountException e) {
			// TODO: handle exception
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, e.getMessage(), e);
		}

		return ResponseEntity.ok(newCustomers); 
	}

	@PutMapping("/debit/customer/accountid/{acc_no}") 
	public ResponseEntity<Transactions> debit(@RequestBody Amount debitAmount, @PathVariable("acc_no") long acc_no) throws InSufficientException{
		
		Transactions newCustomers=null;
		
		try {
			newCustomers = (Transactions)bankingService.debit(acc_no, debitAmount.getDebit_amount());
		} catch (InActiveAccountException e) {
			// TODO: handle exception
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
		catch (InSufficientException e) {
			// TODO: handle exception
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, e.getMessage(), e);
		}

		return ResponseEntity.ok(newCustomers); 
	}

	@GetMapping("/balance/customer/accountid/{acc_no}") 
	public ResponseEntity<String> balance(@PathVariable("acc_no") long acc_no) {

		String balance = "";
		
		try {
			balance = bankingService.balance(acc_no).toString();
		} catch (InActiveAccountException e) {
			// TODO: handle exception
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
		
		return ResponseEntity.ok(balance); 
	}

	@GetMapping("/close/customer/accountid/{acc_no}") 
	public ResponseEntity<Customers> close(@PathVariable("acc_no") long acc_no) {

		Customers newCustomers = null;
		newCustomers = (Customers) bankingService.close(acc_no);

		return ResponseEntity.ok(newCustomers); 
	}

}
