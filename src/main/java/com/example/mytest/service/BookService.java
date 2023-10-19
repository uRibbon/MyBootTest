package com.example.mytest.service;

import com.example.mytest.dto.BookRequestDTO;
import com.example.mytest.dto.BookRequestForm;
import com.example.mytest.dto.BookResponseDTO;
import com.example.mytest.entity.Book;
import com.example.mytest.exception.BusinessException;
import com.example.mytest.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final ModelMapper modelMapper;


    // 등록
    public BookResponseDTO regBook(BookRequestDTO bookRequestDTO) {
        Book book = modelMapper.map(bookRequestDTO, Book.class);
        Book savedBook = bookRepository.save(book);
        return modelMapper.map(savedBook, BookResponseDTO.class);
    }


    // 전체 조회
    @Transactional(readOnly = true)
    public List<BookResponseDTO> getBooks() {
        List<Book> bookList = bookRepository.findAll();

        List<BookResponseDTO> bookResponseDTOList = bookList.stream()
                .map(book -> modelMapper.map(book, BookResponseDTO.class))
                .collect(toList());

        return bookResponseDTOList;
    }


    // Id로 조회
    @Transactional(readOnly = true)
    public BookResponseDTO getBookById(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException(id + "Book Is Not Found", HttpStatus.FOUND));
        BookResponseDTO bookResponseDTO = modelMapper.map(book, BookResponseDTO.class);
        return bookResponseDTO;
    }

    // isbn으로 조회
    @Transactional(readOnly = true)
    public BookResponseDTO getBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BusinessException(isbn + "Book Is Not Found", HttpStatus.FOUND));
        BookResponseDTO bookResponseDTO = modelMapper.map(book, BookResponseDTO.class);
        return bookResponseDTO;
    }

    // 수정
    public BookResponseDTO updateBook(Integer id, BookRequestDTO bookRequestDTO) {
        Book existBook = bookRepository.findById(id).orElseThrow(() ->
                new BusinessException(id + "Book Is Not Found", HttpStatus.NOT_FOUND));
        if(bookRequestDTO.getTitle() != null) {
            existBook.setTitle(bookRequestDTO.getTitle());
        }
        if(bookRequestDTO.getAuthor() != null) {
            existBook.setAuthor(bookRequestDTO.getAuthor());
        }
        return modelMapper.map(existBook, BookResponseDTO.class);
    }

    // 삭제
    public void deleteBook(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException(id + "Book Is Not Found", HttpStatus.NOT_FOUND));
        bookRepository.delete(book);
    }

    // form 수정
    public void updateBookForm(BookRequestForm bookRequestForm) {
        Book existBook = bookRepository.findById(bookRequestForm.getId()).orElseThrow(() ->
                new BusinessException(bookRequestForm.getId() + "Book Is Not Found", HttpStatus.NOT_FOUND));
        existBook.setTitle(bookRequestForm.getTitle());
    }

}
