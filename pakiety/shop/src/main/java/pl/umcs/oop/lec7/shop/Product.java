package pl.umcs.oop.lec7.shop;

import java.math.BigDecimal;

public class Product {
    protected String name;
    protected BigDecimal price;
    protected Integer id;

    public Product(String name, BigDecimal price, int id) {
        this.name = name;
        this.price = price;
        this.id = id;
    }
}
