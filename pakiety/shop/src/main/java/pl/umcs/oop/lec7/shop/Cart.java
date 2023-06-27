package pl.umcs.oop.lec7.shop;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.DatabaseConnection;

public class Cart<T extends Product> {
    protected List<T> products = new ArrayList<>();
    protected Account owner;

    public Cart(Account owner) {
        this.owner = owner;
    }

    public void add(T product) {
        products.add(product);
    }

    public BigDecimal value() {
        return products.stream()
                .map(product -> product.price)
                .reduce(new BigDecimal(0), BigDecimal::add);
    }
    public String buy(int id) throws SQLException {
        Warehouse.deleteData(id);
        Map<String, Object> cartData = new HashMap<>();
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
