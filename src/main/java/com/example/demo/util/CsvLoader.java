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
                if (parts.length < 5) continue;

                Movie movie = new Movie();
                movie.setReleaseYear(Integer.parseInt(parts[0]));
                movie.setTitle(parts[1]);
                movie.setStudios(parts[2]);
                movie.setProducers(parts[3]);
                movie.setWinner("yes".equalsIgnoreCase(parts[4].trim()));

                movieRepository.save(movie);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
