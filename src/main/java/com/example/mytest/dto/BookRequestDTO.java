package com.example.mytest.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BookRequestDTO {


    @NotEmpty(message = "title은 필수 입력 항목입니다!!!")
    private String title;

    @NotEmpty(message = "author은 필수 입력 항목입니다!!!")
    private String author;

    @NotEmpty(message = "isbn은 필수 입력 항목입니다!!!")
    private String isbn;

    @NotEmpty(message = "genre은 필수 입력 항목입니다!!!")
    private String genre;


}
