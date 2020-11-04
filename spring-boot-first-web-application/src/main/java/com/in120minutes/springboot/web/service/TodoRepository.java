package com.in120minutes.springboot.web.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in120minutes.springboot.web.model.Todo;


public interface TodoRepository extends JpaRepository<Todo, Long>{
  List<Todo> findByUser(String user);
}
