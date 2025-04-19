package com.example.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.dto.AwardIntervalResponseDTO;
import com.example.demo.dto.ProducerIntervalDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProducerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testProducerIntervalResponse() throws Exception {
        String json = mockMvc.perform(get("/producers/intervals"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        AwardIntervalResponseDTO response = objectMapper.readValue(json, AwardIntervalResponseDTO.class);

        List<ProducerIntervalDTO> minList = response.getMin();
        List<ProducerIntervalDTO> maxList = response.getMax();

        assertThat(minList).isNotEmpty();
        assertThat(maxList).isNotEmpty();

        for (ProducerIntervalDTO dto : minList) {
            assertThat(dto.getInterval()).isGreaterThanOrEqualTo(0);
        }

        for (ProducerIntervalDTO dto : maxList) {
            assertThat(dto.getInterval()).isGreaterThanOrEqualTo(0);
        }
    }
}
