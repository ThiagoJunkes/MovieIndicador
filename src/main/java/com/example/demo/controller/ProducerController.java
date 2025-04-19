package com.example.demo.controller;

import com.example.demo.dto.AwardIntervalResponseDTO;
import com.example.demo.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producers")
@RequiredArgsConstructor
public class ProducerController {

    private final ProducerService producerService;

    @GetMapping("/intervals")
    public AwardIntervalResponseDTO getProducerIntervals() {
        return producerService.getProducerAwardIntervals();
    }
}
