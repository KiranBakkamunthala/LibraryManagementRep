package com.collaberadigital.library.management.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collaberadigital.library.management.model.Book;
import com.collaberadigital.library.management.model.BorrowRecord;
import com.collaberadigital.library.management.model.Borrower;
import com.collaberadigital.library.management.repository.BookRepository;
import com.collaberadigital.library.management.repository.BorrowRecordRepository;
import com.collaberadigital.library.management.repository.BorrowerRepository;

@Service
public class BorrowRecordService {

	@Autowired
	private BorrowRecordRepository borrowRecordRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BorrowerRepository borrowerRepository;

	public BorrowRecord borrowBook(Long borrowerId, String isbn) {
		Book book = bookRepository.findByIsbn(isbn);
		if (book == null) {
			throw new RuntimeException("Book not found");
		}

		Optional<BorrowRecord> existingRecord = borrowRecordRepository.findByBookAndReturned(book, false);
		if (existingRecord.isPresent()) {
			throw new RuntimeException("Book is already borrowed");
		}

		Borrower borrower = borrowerRepository.findById(borrowerId)
				.orElseThrow(() -> new RuntimeException("Borrower not found"));

		BorrowRecord borrowRecord = new BorrowRecord();
		borrowRecord.setBook(book);
		borrowRecord.setBorrower(borrower);
		borrowRecord.setReturned(false);

		return borrowRecordRepository.save(borrowRecord);
	}

	public void returnBook(Long borrowRecordId) {
		BorrowRecord borrowRecord = borrowRecordRepository.findById(borrowRecordId)
				.orElseThrow(() -> new RuntimeException("Borrow record not found"));
		borrowRecord.setReturned(true);
		borrowRecordRepository.save(borrowRecord);
	}
	
	
}
