package com.example.mysql.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mysql.model.Book;

import jakarta.transaction.Transactional;

public interface BookRepository extends JpaRepository<Book, Long> {

	public List<Book> findByBookname(String name);

	public Optional<Book> findByBookid(Long id);

	@Transactional
	public Optional<Book> deleteByBookid(Long id);
	
	

}
