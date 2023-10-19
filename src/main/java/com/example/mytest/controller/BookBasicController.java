package com.example.mytest.controller;

import com.example.mytest.entity.Book;
import com.example.mytest.exception.BusinessException;
import com.example.mytest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/books")
public class BookBasicController {

    @Autowired
    private BookRepository bookRepository;


    // 등록
    @PostMapping
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    // 전체 조회
    @GetMapping
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }


    // id로 조회
    @RequestMapping(value = "/{id}")
    public Book getBook(@PathVariable Integer id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Book Is Not Found", HttpStatus.NOT_FOUND));
    }

    // isbn으로 조회
    @RequestMapping(value = "/isbn/{isbn}")
    public Book getBook(@PathVariable String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BusinessException("Book Is Not Found", HttpStatus.NOT_FOUND));
    }

    // 수정
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Integer id, @RequestBody Book bookDetail) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("User Not Found", HttpStatus.NOT_FOUND));

        book.setTitle(bookDetail.getTitle());
        book.setAuthor(bookDetail.getAuthor());
        Book updatedBook = bookRepository.save(book);
        return updatedBook;
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Customer Not Found", HttpStatus.NOT_FOUND));
        bookRepository.delete(book);
        return ResponseEntity.ok().build();
    }
}
