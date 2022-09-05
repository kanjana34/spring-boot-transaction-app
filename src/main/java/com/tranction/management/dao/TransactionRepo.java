package com.tranction.management.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tranction.management.entity.Transactions;
@Repository
public interface TransactionRepo extends JpaRepository<Transactions, Integer>{

}
