package com.hacker.shift_service.controller;

import com.hacker.shift_service.dto.JobIdDTO;
import com.hacker.shift_service.dto.ShiftReqDTO;
import com.hacker.shift_service.dto.ShiftResDTO;
import com.hacker.shift_service.handler.BaseResponse;
import com.hacker.shift_service.service.ShiftService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/shift")
@RequiredArgsConstructor
@Tag(name = "Shift API", description = "Handles array shifting operations")
public class ShiftController {

    private final ShiftService shiftService;

    @PostMapping
    @Operation(summary = "Create a shift job", description = "Submits an array and shift value, returning a jobId")
    public BaseResponse<JobIdDTO> createShiftJob(@Valid @RequestBody ShiftReqDTO request) {
        return BaseResponse.success(new JobIdDTO(shiftService.createShiftJob(request.getArray(), request.getN())));
    }

    @GetMapping("/{jobId}")
    @Operation(summary = "Get shift job status", description = "Retrieves the status of a shift job by jobId")
    public BaseResponse<ShiftResDTO> getShiftJobStatus(@PathVariable String jobId) {
        try {
            return BaseResponse.success(shiftService.getJobStatus(jobId));
        } catch (IllegalArgumentException e) {
            return BaseResponse.error(HttpStatus.BAD_REQUEST, "Invalid jobId format");
        }
    }
}