package com.hacker.shift_service.unit.service;

import com.hacker.shift_service.service.ShiftService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ShiftServiceTest {

    @InjectMocks
    private ShiftService shiftService;

    @Test
    void testShiftBy() {

        int[] input1 = {1, 2, 3, 4};
        int[] expected1 = {3, 4, 1, 2};
        assertArrayEquals(expected1, shiftService.shiftBy(input1, 2));

        int[] emptyInput = {};
        int[] expectedEmpty = {};
        assertArrayEquals(expectedEmpty, shiftService.shiftBy(emptyInput, 2));

        int[] input2 = {1, 2, 3};
        int[] expected2 = {1, 2, 3};
        assertArrayEquals(expected2, shiftService.shiftBy(input2, 0));

        int[] input3 = {1, 2, 3};
        int[] expected3 = {2, 3, 1};
        assertArrayEquals(expected3, shiftService.shiftBy(input3, -1));

        int[] input4 = {1, 2, 3, 4};
        int[] expected4 = {3, 4, 1, 2};
        assertArrayEquals(expected4, shiftService.shiftBy(input4, 6));

        int[] input5 = {1, 2, 3, 4};
        int[] expected5 = {3, 4, 1, 2};
        assertArrayEquals(expected5, shiftService.shiftBy(input5, -6));
    }

    @Test
    void testShiftByWithNull() {
        assertThrows(NullPointerException.class, () -> shiftService.shiftBy(null, 2));
    }
}