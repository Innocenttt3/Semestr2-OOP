package pl.umcs.oop.lec7.shop;

import database.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    static DatabaseConnection connection;
    public record ProductAmount(Product product, int amount) {
    }
    public List<ProductAmount> findProduct(String part) throws SQLException {
        PreparedStatement statement = connection.getConnection().prepareStatement("SELECT product.id, product.name, product.price, warehouse.amount FROM shop_product AS product JOIN shop_warehouse AS warehouse ON product.id = warehouse.id WHERE product.name LIKE ?;");
        statement.setString(1, part+"%");
        statement.execute();
        ResultSet result = statement.getResultSet();

        List<ProductAmount> productAmounts = new ArrayList<>();
        while(result.next()) {
            String name = result.getString("name");
            BigDecimal amount = result.getBigDecimal("amount");

            Product product = new Product(name, amount, result.getInt("id"));
        }
        return productAmounts;
    }
    public static void deleteData(int id) {
       connection.deleteProduct();
    }
}
