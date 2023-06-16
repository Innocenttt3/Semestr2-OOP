package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.sqlite.JDBC;



public class Main {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/kamilgolawski/Nauka/Programowanie/Semestr2-OOP/database/firstDataBase.db")) {
            System.out.println("podlaczono baze danych");
            String createTableSql = "CREATE TABLE dot (" +
                    "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "x INTEGER NOT NULL," +
                    "y INTEGER NOT NULL," +
                    "color TEXT NOT NULL," +
                    "radius INTEGER NOT NULL);";
            connection.createStatement().executeUpdate(createTableSql);
            System.out.println("utworzono tabele");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



