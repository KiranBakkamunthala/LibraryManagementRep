package com.collaberadigital.library.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collaberadigital.library.management.model.Borrower;
import com.collaberadigital.library.management.repository.BorrowerRepository;

@Service
public class BorrowerService {

	@Autowired
	private BorrowerRepository borrowerRepository;

	public Borrower saveBorrower(Borrower borrower) {
		return borrowerRepository.save(borrower);
	}

	public List<Borrower> getAllBorrowers() {
		return borrowerRepository.findAll();
	}
}
