package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MovieDTO;
import com.example.demo.model.Movie;
import com.example.demo.repository.MovieRepository;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public void saveAll(List<MovieDTO> movieDTOs) {
    List<Movie> movies = new ArrayList<>();

    for (MovieDTO dto : movieDTOs) {
        Movie movie = new Movie();
        movie.setReleaseYear(dto.getReleaseYear());
        movie.setTitle(dto.getTitle());
        movie.setStudios(dto.getStudios());
        movie.setProducers(dto.getProducers());
        movie.setWinner(dto.getWinner());

        if (!movie.validateMovie()) continue;

        System.out.println("Loading Movie: " + movie.toString());
        movies.add(movie);
    }

    movieRepository.saveAll(movies);
}
}
