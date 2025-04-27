package com.example.demo.controller;

import com.example.demo.dto.AwardIntervalResponseDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@SpringBootTest
@AutoConfigureMockMvc
class ProducerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testProducerIntervalResponse_ShouldMatchExpectedData() throws Exception {
        // Chama API
        String actualJson = mockMvc.perform(get("/producers/intervals"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        InputStream is = getClass().getResourceAsStream("/expected/expected-test-result.json");
        String expectedJson = new String(is.readAllBytes(), StandardCharsets.UTF_8);

        AwardIntervalResponseDTO actualResponse = objectMapper.readValue(actualJson, new TypeReference<>() {});
        AwardIntervalResponseDTO expectedResponse = objectMapper.readValue(expectedJson, new TypeReference<>() {});

        // Compara se objeto está EXATAMENTE igual ao esperado
        assertThat(actualResponse)
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(expectedResponse);
    }
}
