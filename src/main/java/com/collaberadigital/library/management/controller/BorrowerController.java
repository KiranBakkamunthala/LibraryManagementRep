package com.collaberadigital.library.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.collaberadigital.library.management.model.Borrower;
import com.collaberadigital.library.management.service.BorrowerService;

@RestController
@RequestMapping("/borrowers")
@Validated
public class BorrowerController {

	@Autowired
	private BorrowerService borrowerService;

	@PostMapping
	public ResponseEntity<Borrower> createBorrower(@RequestBody Borrower borrower) {
		return ResponseEntity.ok(borrowerService.saveBorrower(borrower));
	}

	@GetMapping
	public ResponseEntity<List<Borrower>> getAllBorrowers() {
		return ResponseEntity.ok(borrowerService.getAllBorrowers());
	}
}
