package com.tranction.management.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tranction.management.entity.Customers;
@Repository
public interface CustomerRepo extends JpaRepository<Customers, Integer>{
	@Query(value = "select * from customers where customer_id=?1",nativeQuery = true)
	public Customers getCustomersById(@Param("customer_id") long customerId);
	@Query(value = "select * from customers where account_id=?1",nativeQuery = true)
	public Customers getCustomersByAccId(@Param("account_id") long account_id);

}
