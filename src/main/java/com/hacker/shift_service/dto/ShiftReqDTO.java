package com.hacker.shift_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShiftReqDTO {

    @NotNull(message = "Array can't be null")
    private int[] array;

    @NotNull(message = "n can't be null")
    private Integer n;
}