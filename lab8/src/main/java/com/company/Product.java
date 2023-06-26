package com.company;

public abstract class Product {
    public String name;

    public Product(String name) {
    }

    public void setName(String name) {
        this.name = name;
    }
    public abstract double getPrice(int year, int month);

    public static int getIndex(int year, int month){
        if(year < 2010 || year > 2022) throw new IndexOutOfBoundsException(year);
        if(year == 2022 & month > 3) throw new IndexOutOfBoundsException(year);
        int index = year - 2010;
        index *= 12;
        index += month;
        index -= 1;
        return index;
    }
}
