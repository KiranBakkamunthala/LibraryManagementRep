package com.collaberadigital.library.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.collaberadigital.library.management.model.Borrower;

public interface BorrowerRepository extends JpaRepository<Borrower, Long> {
}
