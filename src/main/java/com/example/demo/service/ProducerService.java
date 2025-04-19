package com.example.demo.service;

import com.example.demo.dto.AwardIntervalResponseDTO;
import com.example.demo.dto.ProducerIntervalDTO;
import com.example.demo.model.Movie;
import com.example.demo.repository.MovieRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProducerService {

    private final MovieRepository movieRepository;

    public AwardIntervalResponseDTO getProducerAwardIntervals() {
        List<Movie> winners = movieRepository.findByWinnerTrue();

        Map<String, List<Integer>> producerWins = new HashMap<>();

        // Separando os produtores e os anos de
        for (Movie movie : winners) {
            if(!movie.getWinner()) continue;
            String producersField = movie.getProducers();
            String[] producersSplit = producersField.split(",| and ");

            for (String producer : producersSplit) {
                producer = producer.trim();

                if (!producerWins.containsKey(producer)) {
                    producerWins.put(producer, new ArrayList<>());
                }

                producerWins.get(producer).add(movie.getReleaseYear());
            }
        }

        List<ProducerIntervalDTO> intervals = new ArrayList<>();

        for (String producer : producerWins.keySet()) {
            List<Integer> years = producerWins.get(producer);

            if (years.size() >= 2) {
                Collections.sort(years);

                for (int i = 0; i < years.size() - 1; i++) {
                    int previousWin = years.get(i);
                    int nextWin = years.get(i + 1);
                    int interval = nextWin - previousWin;

                    ProducerIntervalDTO dto = new ProducerIntervalDTO(producer, interval, previousWin, nextWin);
                    intervals.add(dto);
                }
            }
        }

        int minInterval = 0;
        int maxInterval = 0;

        if (!intervals.isEmpty()) {
            minInterval = intervals.get(0).getInterval();
            maxInterval = intervals.get(0).getInterval();

            for (ProducerIntervalDTO interval : intervals) {
                if (interval.getInterval() < minInterval) {
                    minInterval = interval.getInterval();
                }

                if (interval.getInterval() > maxInterval) {
                    maxInterval = interval.getInterval();
                }
            }
        }

        List<ProducerIntervalDTO> minList = new ArrayList<>();
        List<ProducerIntervalDTO> maxList = new ArrayList<>();

        for (ProducerIntervalDTO interval : intervals) {
            if (interval.getInterval() == minInterval) {
                minList.add(interval);
            }

            if (interval.getInterval() == maxInterval) {
                maxList.add(interval);
            }
        }

        AwardIntervalResponseDTO response = new AwardIntervalResponseDTO();
        response.setMin(minList);
        response.setMax(maxList);

        return response;
    }
}
