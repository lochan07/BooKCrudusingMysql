package com.example.mysql.controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mysql.model.Book;
import com.example.mysql.repository.BookRepository;

@RestController
public class BookController {

	@Autowired
	BookRepository bookRepository;

	@PostMapping("/bookSave")
	public Book insertBook(@RequestParam("book_name") String name, @RequestParam("book_author") String author) {

		Logger logger = Logger.getLogger(getClass().getName());
//		logger.info("More testing..." + book.getBookid() + " " + name. + " " + book.getBookauthor());
		Book book = new Book();
		book.setBookname(name);
		book.setBookauthor(author);
		return bookRepository.save(book);
//		return "Your record is saved successfully..";
	}

	@PostMapping("/multiplebookSave")
	public String insertBook(@RequestBody List<Book> book) {
		bookRepository.saveAll(book);
		return "All records are saved successfully..";
	}

	@GetMapping("/getallbooks")
	public List<Book> getBook() {
		return (List<Book>) bookRepository.findAll();
	}

	@GetMapping("/getbybookname/{name}")
	public List<Book> getBook(@PathVariable("name") String bookname) {
		return (List<Book>) bookRepository.findByBookname(bookname);
	}

	@GetMapping("/getbybookid/{id}")
	public Optional<Book> getBook(@PathVariable("id") Long bookid) {
		return bookRepository.findByBookid(bookid);
	}

	@DeleteMapping("/deletebybookid/{id}")
	public Optional<Book> deleteBook(@PathVariable("id") Long bookid) {
		return bookRepository.deleteByBookid(bookid);
	}

	@PutMapping("/updatebybookid/{id}")
	public String updateBook(@PathVariable("id") Long bookid, @RequestParam("book_name") String name,
			@RequestParam("book_author") String author) {
		Optional<Book> book = bookRepository.findById(bookid);
		book.get().setBookname(name);
		book.get().setBookauthor(author);
		bookRepository.save(book.get());
		return "Record is updated";
	}

}
