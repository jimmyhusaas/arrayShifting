package com.hacker.shift_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ShiftResDTO {
    private String jobId;
    private String status;
    private int[] result;
}