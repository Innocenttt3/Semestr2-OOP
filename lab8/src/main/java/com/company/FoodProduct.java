package com.company;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class FoodProduct extends Product {
    HashMap<String, Double[]> prices;

    protected FoodProduct(String name,  HashMap<String, Double[]> prices) {
        super(name);
        this.prices = prices;
    }

    public static FoodProduct fromCsv(Path path) {
        String name;
        HashMap<String, Double[]> prices = new HashMap<>();
        try {
            Scanner scanner = new Scanner(path);
            name = scanner.nextLine();
            scanner.nextLine();

            while(scanner.hasNext()) {
                String lineData[] = scanner.nextLine().split(";");
                String provinceName = lineData[0];

                Double[] provincePrices = Arrays.stream(lineData)
                        .skip(1)
                        .map(value -> value.replace(",","."))
                        .map(Double::valueOf)
                        .toArray(Double[]::new);

                prices.put(provinceName, provincePrices);
            }
            scanner.close();

            return new FoodProduct(name, prices);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public double getPrice(int year, int month) {
        int index = getIndex(year, month);
        double sum = 0;
        for(Double[] provincePrices : prices.values())
            sum += provincePrices[index];

        return sum/prices.size();
    }
}