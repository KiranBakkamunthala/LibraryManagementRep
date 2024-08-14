package com.collaberadigital.library.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collaberadigital.library.management.model.Book;
import com.collaberadigital.library.management.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}

	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	public Book getBookByIsbn(String isbn) {
		return bookRepository.findByIsbn(isbn);
	}
}
