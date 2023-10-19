package com.example.mytest.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BookResponseDTO {
    private Integer id;
    private String title;
    private String author;
    private String isbn;
    private String genre;
    private LocalDateTime createdAt;
}