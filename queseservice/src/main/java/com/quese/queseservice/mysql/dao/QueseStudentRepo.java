package com.quese.queseservice.mysql.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quese.queseservice.mysql.entity.QueseStudentName;

public interface QueseStudentRepo extends JpaRepository<QueseStudentName, Long> {
	Optional findById(Long id);

}
