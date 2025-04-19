package com.example.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class AwardIntervalResponseDTO {
    private List<ProducerIntervalDTO> min;
    private List<ProducerIntervalDTO> max;
}