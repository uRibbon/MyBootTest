package com.example.mytest.controller;

import com.example.mytest.dto.BookRequestDTO;
import com.example.mytest.dto.BookRequestForm;
import com.example.mytest.dto.BookResponseDTO;
import com.example.mytest.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/bookspage")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/index")
    public ModelAndView index() {
        List<BookResponseDTO> bookResponseDTOList = bookService.getBooks();
        return new ModelAndView("index", "books", bookResponseDTOList);

    }

    // 등록 페이지 로딩
    @GetMapping("/signup")
    public String showSignUpForm(BookRequestDTO bookRequestDTO) {
        return "add-book";
    }


    // 입력 항목 검증 후 등록 처리
    @PostMapping("/addbook")
    public String addBook(@Valid BookRequestDTO bookRequestDTO, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "add-book";
        }

        bookService.regBook(bookRequestDTO);
        model.addAttribute("books", bookService.getBooks());
        return "index";
    }

    // 수정 페이지 호출
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        BookResponseDTO bookResponseDTO = bookService.getBookById(id);
        model.addAttribute("book", bookResponseDTO);
        return "update-book";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") Integer id, @Valid BookRequestForm bookRequestForm, BindingResult result) {
        if (result.hasErrors()) {
            bookRequestForm.setId(id);
            return "update-book";
        }

        // 수정 요청
        bookService.updateBookForm(bookRequestForm);

        // 잘되면 index로 바로 연결시켜줌
        return "redirect:/bookspage/index";
    }

    // 삭제
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Integer id) {
        bookService.deleteBook(id);

        // 삭제가 잘 되면 index로 바로 연결시켜준다.
        return "redirect:/bookspage/index";
    }
}