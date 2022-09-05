package com.tranction.management.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
	@ExceptionHandler(value=InstantiationException.class)
public ResponseEntity<?> getInSufficientException(Exception exception){
	Map<String, String> map=new HashMap<>();
	map.put("ErrorCode", "1001");
	map.put("msg", "Account has inSufficient Balance");
	return ResponseEntity.ok(map);
}
}
