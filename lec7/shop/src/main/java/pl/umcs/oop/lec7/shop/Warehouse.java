package pl.umcs.oop.lec7.shop;

import at.favre.lib.crypto.bcrypt.BCrypt;
import pl.umcs.oop.lec7.database.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Warehouse {
    public record ProductAmount(Product product, int amount) {
    }
    DatabaseConnection connection;

    public Warehouse(DatabaseConnection connection) {
        this.connection = connection;
    }

    public void addProduct(int id, int amount) throws SQLException {
        PreparedStatement statement = connection.getConnection().prepareStatement("SELECT amount FROM shop_warehouse WHERE id = ?;");
        statement.setInt(1, id);
        statement.execute();

        ResultSet result = statement.getResultSet();
        if (result.next()) {
            int currentAmount = result.getInt("amount");
            connection.getConnection().prepareStatement("UPDATE shop_warehouse SET amount = ? WHERE id = ?;");
            statement.setInt(1, currentAmount + amount);
            statement.setInt(2, id);
        } else {
            statement = connection.getConnection().prepareStatement("INSERT INTO shop_warehouse(id, amount) VALUES (?, ?);");
            statement.setInt(1, id);
            statement.setInt(2, amount);
        }
        statement.executeUpdate();
    }

    public List<ProductAmount> findProduct(String part) throws SQLException {
        PreparedStatement statement = connection.getConnection().prepareStatement("SELECT product.id, product.name, product.price, warehouse.amount FROM shop_product AS product JOIN shop_warehouse AS warehouse ON product.id = warehouse.id WHERE product.name LIKE ?;");
        statement.setString(1, part+"%");
        statement.execute();
        ResultSet result = statement.getResultSet();

        List<ProductAmount> productAmounts = new ArrayList<>();
        while(result.next()) {
            int id = result.getInt("id");
            String name = result.getString("name");
            BigDecimal price = result.getBigDecimal("price");
            int amount = result.getInt("amount");

            Product product = new Product(id, name, price);
            productAmounts.add(new ProductAmount(product, amount));
        }
        return productAmounts;
    }

    public void removeProducts(List<Product> products) throws SQLException {
        Map<Integer, Integer> cartProductCounts = new HashMap<>();
        for(Product product : products) {
            if(cartProductCounts.containsKey(product.getId()))
                cartProductCounts.put(product.getId(), cartProductCounts.get(product.getId()) + 1);
            else
                cartProductCounts.put(product.getId(), 1);
        }

        Map<Integer, Integer> warehouseProductCounts = new HashMap<>();

//        Array cartProductKeysArray = connection.getConnection().createArrayOf("INTEGER", cartProductCounts.keySet().toArray());
//        PreparedStatement statement = connection.getConnection().prepareStatement("SELECT id, amount FROM shop_warehouse WHERE id IN (?);");
//        statement.setArray(1, cartProductKeysArray);

        String keyString = cartProductCounts.keySet().stream()
                .map(o -> o.toString())
                .collect(Collectors.joining(","));
        PreparedStatement statement = connection.getConnection().prepareStatement(String.format("SELECT id, amount FROM shop_warehouse WHERE id IN (%s);", keyString));

        statement.execute();
        ResultSet result = statement.getResultSet();

        while(result.next()) {
            Integer id = result.getInt(1);
            Integer amount = result.getInt(2);
            warehouseProductCounts.put(id, amount);
        }

        if(!warehouseProductCounts.keySet().equals(cartProductCounts.keySet()))
            throw new RuntimeException("There are unavailable products");

        connection.getConnection().setAutoCommit(false);
        for(Integer productId : warehouseProductCounts.keySet()) {
            int warehouseCount = warehouseProductCounts.get(productId);
            int cartCount = cartProductCounts.get(productId);
            if(warehouseCount < cartCount) {
                connection.getConnection().rollback();
                throw new RuntimeException("There is not enough id = " + productId + " products");
            }
            else {
                statement = connection.getConnection().prepareStatement("UPDATE shop_warehouse SET amount = ? WHERE id = ?;");
                statement.setInt(1, warehouseCount - cartCount);
                statement.setInt(2, productId);
                statement.executeUpdate();
            }
        }
        connection.getConnection().commit();
        connection.getConnection().setAutoCommit(true);

    }
}
