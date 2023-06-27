package com.company;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        NonFoodProduct nonFoodProduct = NonFoodProduct.fromCsv(Paths.get("/Users/kamilgolawski/Nauka/Programowanie/Java/java_lab_2023-master/lab8/data/nonfood/benzyna.csv"));
        System.out.println(nonFoodProduct.priceIndex(2010, 2));
    }


}
