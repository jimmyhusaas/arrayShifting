package com.hacker.shift_service.service;


import com.hacker.shift_service.dto.JobIdDTO;
import com.hacker.shift_service.dto.ShiftResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;




@Service
@Slf4j
public class ShiftService {
    private final Map<String, int[]> jobResults = new ConcurrentHashMap<>();

    public String createShiftJob(int[] array, int n) {
        String jobId = UUID.randomUUID().toString();
        log.info("Created job: {}", jobId);

        int[] result = processShift(array, n);
        jobResults.put(jobId, result);
        return jobId;
    }

    public int[] processShift(int[] array, int n) {
        return shiftBy(array, n);
    }

    public ShiftResDTO getJobStatus(String jobId) {
        int[] result = jobResults.get(jobId);
        if (result == null) {
            throw new NoSuchElementException("Job ID not found: " + jobId);
        }
        return new ShiftResDTO(jobId, "complete", result);
    }

    public int[] shiftBy(int[] input, int n) {
        if (input == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }
        int length = input.length;
        if (length == 0 || n % length == 0) {
            return input;
        }
        n = ((n % length) + length) % length;

        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[(i + n) % length] = input[i];
        }
        return result;
    }
}
