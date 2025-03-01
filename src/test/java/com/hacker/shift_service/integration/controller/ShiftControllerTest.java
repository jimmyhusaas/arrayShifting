package com.hacker.shift_service.integration.controller;

import com.hacker.shift_service.dto.ShiftReqDTO;
import com.hacker.shift_service.dto.ShiftResDTO;
import com.hacker.shift_service.service.ShiftService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ShiftControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShiftService shiftService;

    @Test
    void testCreateShiftJob() throws Exception {
        String mockJobId = UUID.randomUUID().toString();
        Mockito.when(shiftService.createShiftJob(any(int[].class), any(Integer.class)))
                .thenReturn(mockJobId);

        mockMvc.perform(post("/shift")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"array\": [1,2,3,4], \"n\": 2}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data.jobId").value(mockJobId));
    }

    @Test
    void testGetShiftJobStatus() throws Exception {
        String jobId = UUID.randomUUID().toString();
        ShiftResDTO response = new ShiftResDTO(jobId, "complete", new int[]{3, 4, 1, 2});

        Mockito.when(shiftService.getJobStatus(eq(jobId))).thenReturn(response);

        mockMvc.perform(get("/shift/" + jobId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data.jobId").value(jobId))
                .andExpect(jsonPath("$.data.status").value("complete"))
                .andExpect(jsonPath("$.data.result[0]").value(3))
                .andExpect(jsonPath("$.data.result[1]").value(4))
                .andExpect(jsonPath("$.data.result[2]").value(1))
                .andExpect(jsonPath("$.data.result[3]").value(2));
    }
}