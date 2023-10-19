package com.example.mytest.repository;

import com.example.mytest.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findBytitle(String title);

    Optional<Book> findById(Integer id);

//    Optional<Book> findByAuthor(String Author);

    Optional<Book> findByIsbn(String Isbn);
}
