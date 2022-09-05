package com.tranction.management.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tranction.management.entity.Transaction_Types;

@Repository
public interface TransactionsTypeRepo extends JpaRepository<Transaction_Types, Integer>{

}
