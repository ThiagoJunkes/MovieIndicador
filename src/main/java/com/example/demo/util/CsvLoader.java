package com.example.demo.util;

import com.example.demo.model.Movie;
import com.example.demo.repository.MovieRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class CsvLoader {

    private final MovieRepository movieRepository;
    private String csvFileName = "/movielist.csv";

    @PostConstruct
    public void loadCsv() {
        if (getClass().getResourceAsStream(csvFileName) == null) {
            System.out.println("CSV File not found!");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream(csvFileName), StandardCharsets.UTF_8))) {

            String line;
            reader.readLine(); // pula cabecalho

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");

                Movie movie = new Movie();
                movie.setReleaseYear(Integer.parseInt(parts[0]));
                movie.setTitle(parts[1]);
                movie.setStudios(parts[2]);
                movie.setProducers(parts[3]);
                if (parts.length < 5) movie.setWinner(false);
                else movie.setWinner(parts[4].trim().equals("yes"));

                movieRepository.save(movie);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
