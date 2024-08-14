package com.collaberadigital.library.management;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.collaberadigital.library.management.model.Book;
import com.collaberadigital.library.management.model.Borrower;
import com.collaberadigital.library.management.repository.BookRepository;
import com.collaberadigital.library.management.repository.BorrowerRepository;
import com.collaberadigital.library.management.service.BorrowRecordService;

@SpringBootTest
@AutoConfigureMockMvc
class LibraryManagementApplicationTests {

	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowerRepository borrowerRepository;

    @Autowired
    private BorrowRecordService borrowRecordService;

    @BeforeEach
    public void setUp() {
        
        bookRepository.deleteAll();
        borrowerRepository.deleteAll();
    }

    @Test
    public void testCreateBook() throws Exception {
        String bookJson = "{\"isbn\":\"123456789\",\"title\":\"Sample Book\",\"author\":\"Author Name\"}";

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson))
                .andExpect(status().isOk());
    }

	/*
	 * @Test public void testCreateBorrower() throws Exception { String borrowerJson
	 * = "{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\"}";
	 * 
	 * mockMvc.perform(post("/borrowers") .contentType(MediaType.APPLICATION_JSON)
	 * .content(borrowerJson)) .andExpect(status().isOk()); }
	 */

    @Test
    public void testBorrowBook() throws Exception {
        
        Book book = new Book();
        book.setIsbn("123456789");
        book.setTitle("Sample Book");
        book.setAuthor("Author Name");
        bookRepository.save(book);

        Borrower borrower = new Borrower();
        borrower.setName("John Doe");
        borrower.setEmail("john.doe@example.com");
        borrowerRepository.save(borrower);

        
        mockMvc.perform(post("/borrow-records/borrow")
                .param("borrowerId", String.valueOf(borrower.getId()))
                .param("isbn", "123456789"))
                .andExpect(status().isOk());
    }

}
