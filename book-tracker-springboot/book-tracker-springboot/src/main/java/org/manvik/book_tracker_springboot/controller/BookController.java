package org.manvik.book_tracker_springboot.controller;

import org.manvik.book_tracker_springboot.booktrackerspringboot.Book;
import org.manvik.book_tracker_springboot.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    //GET /api/books - fetch all
    @GetMapping
    public List<Book> getAllBooks() {
        return  bookRepository.findAll();
    }

    // GET /api/books/{id} - fetch one
    @GetMapping("/{id}")
    public  Book getBookById(@PathVariable int id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    // POST /api/books - create
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    // PUT /api/books/{id} - update
    @PutMapping("/{id}")
    public  Book updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
        updatedBook.setId(id);
        return bookRepository.save(updatedBook);
    }

    // DELETE /api/books/{id} - delete
    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable int id) {
        bookRepository.deleteById(id);
    }



}
