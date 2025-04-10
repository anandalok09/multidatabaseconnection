package com.quese.queseservice.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.quese.queseservice.mysql.dao.QueseStudentRepo;
@Service
public class QueseStudentService {

	@Autowired
	private QueseStudentRepo queseStudentRepo;
    
}
