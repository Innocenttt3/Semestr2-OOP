package com.company;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.company.Product.priceIndex;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ProductTest {

    @Test
    void getPriceIndex() {
        assertEquals(0, Product.priceIndex(2010, 1));
    }

    @Test
    void outOfIndexTest() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> {
                    Product.priceIndex(2009, 3);
                });
    }

    private static Stream<Arguments> monthYearIndex() {
        return Stream.of(
                arguments(2012, 12, 35),
                arguments(2022, 3, 146),
                arguments(2020, 5, 124)
        );
    }

    @ParameterizedTest
    @MethodSource("monthYearIndex")
    void priceIndex(int year, int month, int expectedIndex) {
        assertEquals(expectedIndex, Product.priceIndex(year, month));
    }
}