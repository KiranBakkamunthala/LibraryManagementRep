package com.collaberadigital.library.management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.collaberadigital.library.management.model.Book;
import com.collaberadigital.library.management.model.BorrowRecord;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
	Optional<BorrowRecord> findByBookAndReturned(Book book, boolean returned);
}