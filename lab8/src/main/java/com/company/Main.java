package com.company;

import java.nio.file.Paths;


public class Main {
    public static void main(String[] args) {

        NonFoodProduct testProduct = NonFoodProduct.fromCsv(Paths.get("/Users/kamilgolawski/Nauka/Programowanie/Semestr2-OOP/lab8/data/nonfood/benzyna.csv"));
        System.out.println(testProduct.prices);

    }
}

