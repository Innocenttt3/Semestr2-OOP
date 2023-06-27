package pl.umcs.oop.lec7.shop;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    protected List<Product> products = new ArrayList<>();
    protected Account owner;
    protected Warehouse warehouse;

    public Cart(Account owner, Warehouse warehouse) {
        this.owner = owner;
        this.warehouse = warehouse;
    }

    public void add(Product product) {
        products.add(product);
    }

    public BigDecimal value() {
        return products.stream()
                .map(product -> product.price)
                .reduce(new BigDecimal(0), BigDecimal::add);
    }

    public String buy() throws SQLException {
        warehouse.removeProducts(this.products);
        Map<String, Object> cartData = new HashMap<>();
        cartData.put("owner", owner);
        cartData.put("products", products);
        cartData.put("value", value());
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cartData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
