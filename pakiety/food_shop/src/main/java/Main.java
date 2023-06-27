import pl.umcs.oop.lec7.auth.AccountManager;
import pl.umcs.oop.lec7.shop.Account;
import pl.umcs.oop.lec7.shop.Cart;
import pl.umcs.oop.lec7.shop.Product;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    //po przenieseniu na inny komputer/pobraniu z repo nie builduja sie package bo maja ustawione sciezki i
    // trzeba to zmienci recznie zeby kod sie wykonywal poprawnie!
    public static void main(String[] args) throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        connection.connect();
//        connection.execStatement();
        connection.execStatementForZad4a();
        connection.execStatementForZad4b();
        }

}