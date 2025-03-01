package com.hacker.shift_service.unit.service;

import com.hacker.shift_service.service.ShiftService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ShiftServiceTest {

    @InjectMocks
    private ShiftService shiftService;

    @Test
    void testShiftBy() {
        // 正常旋轉
        assertArrayEquals(new int[]{3, 4, 1, 2}, shiftService.shiftBy(new int[]{1, 2, 3, 4}, 2));

        // 空陣列
        assertArrayEquals(new int[]{}, shiftService.shiftBy(new int[]{}, 2));

        // 旋轉 0 次應該不變
        assertArrayEquals(new int[]{1, 2, 3}, shiftService.shiftBy(new int[]{1, 2, 3}, 0));

        // 負數旋轉（左移 1，等效於右移 `length - 1`）
        assertArrayEquals(new int[]{2, 3, 1}, shiftService.shiftBy(new int[]{1, 2, 3}, -1));

        // 超過長度的旋轉，等效於 `n % length`
        assertArrayEquals(new int[]{3, 4, 1, 2}, shiftService.shiftBy(new int[]{1, 2, 3, 4}, 6));

        // 負數超過長度的旋轉
        assertArrayEquals(new int[]{3, 4, 1, 2}, shiftService.shiftBy(new int[]{1, 2, 3, 4}, -6));
    }

    @Test
    void testShiftByWithNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> shiftService.shiftBy(null, 2));
        assertEquals("Input array cannot be null", exception.getMessage());
    }
}