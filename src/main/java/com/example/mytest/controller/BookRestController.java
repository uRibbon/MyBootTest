package com.example.mytest.controller;

import com.example.mytest.dto.BookRequestDTO;
import com.example.mytest.dto.BookResponseDTO;
import com.example.mytest.service.BookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;

    private final ModelMapper modelMapper;

    // 등록
    @PostMapping
    public BookResponseDTO regBook(@RequestBody BookRequestDTO bookRequestDTO) {
        return bookService.regBook(bookRequestDTO);
    }

    // 전체 조회
    @GetMapping
    public List<BookResponseDTO> getBooks() {
        return bookService.getBooks();
    }

    // id로 조회
    @GetMapping("/{id}")
    public BookResponseDTO getBookById(@PathVariable Integer id) {
        return bookService.getBookById(id);
    }

    // isbn으로 조회
    @GetMapping("/isbn/{isbn}")
    public BookResponseDTO getBookByIsbn(@PathVariable String isbn) {
        return bookService.getBookByIsbn(isbn);
    }


    // 수정
    @PatchMapping("/{id}")
    public BookResponseDTO updateBook(@PathVariable Integer id, @RequestBody BookRequestDTO bookRequestDTO) {
        return bookService.updateBook(id, bookRequestDTO);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok(id + " 번째 책이 삭제처리 되었습니다.");
    }


}
