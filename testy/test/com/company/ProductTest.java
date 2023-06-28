package com.company;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
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
//zastosowane beforeall/afterall sa wziete z wykladu bo srednio bylo to przedstawione wczesniej
    class zad3stuff {
        static Path tmpDir;

        private static void createFileStub(Path dir, String name) throws IOException {
            File file = File.createTempFile("dataOfProducts", ".csv", dir.toFile());
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);//konieczny komenatrz w mavenie!
            printWriter.println(name);
            printWriter.println();
            printWriter.println();
            printWriter.close();

        }

        @BeforeAll
        static void populateProducts() throws IOException {

            tmpDir = Files.createTempDirectory("dataOfProducts");
            System.out.println(tmpDir);

            createFileStub(tmpDir, "first");
            createFileStub(tmpDir, "second");
            createFileStub(tmpDir, "third");
            createFileStub(tmpDir, "fourth");

            Product.addProducts(FoodProduct::fromCsv, tmpDir);
        }

        @AfterAll
        static void cleanup() {

            for (File file : tmpDir.toFile().listFiles()) {
                System.out.println(file + " deleted.");
                file.delete();
            }
            ;
            tmpDir.toFile().delete();
        }
    }
}