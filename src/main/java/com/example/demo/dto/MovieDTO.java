package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovieDTO {
    private Integer releaseYear;
    private String title;
    private String studios;
    private String producers;
    private Boolean winner;
}
