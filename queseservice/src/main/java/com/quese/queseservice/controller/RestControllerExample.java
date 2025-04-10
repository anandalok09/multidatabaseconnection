package com.quese.queseservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quese.queseservice.mysql.entity.QueseStudentName;
import com.quese.queseservice.service.QueseStudentService;

@RestController
public class RestControllerExample {

	@Autowired
	private QueseStudentService queseStudentService;
	
	@GetMapping(value ="/getqueseservicename" )
	public String getQueseService() {
		return "This is QueseServiceName";
	}
	
	
	
}
