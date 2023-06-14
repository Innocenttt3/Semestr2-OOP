package com.company;

import java.nio.file.Paths;


public class Main {
    public static void main(String[] args) {

        FoodProduct testProduct = FoodProduct.fromCsv(Paths.get("/Users/kamilgolawski/Nauka/Programowanie/Semestr2-OOP/lab8/data/food/buraki.csv"));
        System.out.println(testProduct.name);

    }
}

