package com.company;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class FoodProductTest {
    @Test
    void fromCsvTest() {
        FoodProduct testProduct = FoodProduct.fromCsv(Paths.get("/Users/kamilgolawski/Nauka/Programowanie/Semestr2-OOP/testy-przyklad/data/food/buraki.csv"));
        assertEquals("benzyna", testProduct.getName());
    }

    @Test
    void fromCsvPriceWithGetPrice() {
        FoodProduct product = FoodProduct.fromCsv(Path.of("/Users/kamilgolawski/Nauka/Programowanie/Semestr2-OOP/testy-przyklad/data/food/buraki.csv"));

        assertEquals(1.51, product.getPrice(2010, 3, "LUBELSKIE"));
    }
    @Test
    void fromCsvPriceMapUsage() throws NoSuchFieldException, IllegalAccessException {
        FoodProduct product = FoodProduct.fromCsv(Path.of("/Users/kamilgolawski/Nauka/Programowanie/Semestr2-OOP/testy-przyklad/data/food/buraki.csv"));

        Field pricesField = FoodProduct.class.getDeclaredField("prices");
        pricesField.setAccessible(true);
        Map<String, Double[]> prices = (Map<String, Double[]>) pricesField.get(product);

        assertEquals(1.51, prices.get("yyy")[2]);
    }



}