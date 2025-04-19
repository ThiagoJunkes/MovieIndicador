package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer releaseYear;
    private String title;
    private String studios;
    private String producers;
    private Boolean winner;

    public boolean validateMovie(){
        if(releaseYear == null) return false;
        if(title == null || title.isBlank()) return false;
        if(studios == null || studios.isBlank()) return false;
        if(producers == null || producers.isBlank()) return false;
        if(winner == null) this.winner = false;

        return true;
    }
}
