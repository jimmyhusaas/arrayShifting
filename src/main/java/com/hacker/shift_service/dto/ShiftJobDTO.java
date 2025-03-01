package com.hacker.shift_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.CompletableFuture;


@Data
@NoArgsConstructor
public class ShiftJobDTO {
    private String status = "pending";
    private CompletableFuture<int[]> result = new CompletableFuture<>();

    public ShiftJobDTO(String status) {
        this.status = status;
        this.result = new CompletableFuture<>();
    }

    public void complete(int[] shiftedArray) {
        this.status = "complete";
        this.result.complete(shiftedArray);
    }
}