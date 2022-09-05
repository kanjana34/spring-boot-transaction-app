package com.tranction.management.service;



import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tranction.management.entity.Customers;

public interface BankingTransactionManagementService {
Object create(Customers customers);
  
Object update(Customers customers,long accountNo) throws InActiveAccountException; 
Object credit(long  acc_no,double amount) throws InActiveAccountException; 
Object debit(long acc_no,double amount) throws InActiveAccountException, InSufficientException;
 
Object balance(long acc_no) throws InActiveAccountException;
Object close(long acc_no);

}
