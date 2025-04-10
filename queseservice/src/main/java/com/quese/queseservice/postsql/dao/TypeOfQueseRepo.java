package com.quese.queseservice.postsql.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quese.queseservice.postsql.entity.TypeofQuese;

public interface TypeOfQueseRepo extends JpaRepository<TypeofQuese, Long> {
	Optional findById(Long id);
	
}
