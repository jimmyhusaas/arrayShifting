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

    public int[] shiftBy(int[] array, int n) {
        int len = array.length;
        if (len == 0) return array;

        int shift = ((n % len) + len) % len;
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            result[(i + shift) % len] = array[i];
        }
        return result;
    }
}
