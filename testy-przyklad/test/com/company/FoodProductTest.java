package com.company;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class FoodProductTest {
    @Test
    void fromCsvTest() {
        FoodProduct testProduct = FoodProduct.fromCsv(Paths.get("/Users/kamilgolawski/Nauka/Programowanie/Java/java_lab_2023-master/lab8/data/nonfood/benzyna.csv"));
        assertEquals("benzyna", testProduct.getName());
    }



}