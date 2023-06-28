package pl.umcs.oop.lec7.shop;

import java.math.BigDecimal;

public class Product {
    protected int id;
    protected String name;
    protected BigDecimal price;

    public Product(int id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
