package com.collaberadigital.library.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.collaberadigital.library.management.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	Book findByIsbn(String isbn);
}