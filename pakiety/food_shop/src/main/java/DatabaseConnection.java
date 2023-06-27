import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseConnection implements testInterface{

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public  void connect(){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:/Users/kamilgolawski/Nauka/Programowanie/Semestr2-OOP/database/firstDataBase.db");
            System.out.println("Connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnect(){
        try {
            connection.close();
            System.out.println("Disconnected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void execStatement() throws SQLException {
        String createTableSql = "CREATE TABLE auth_account (\n" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "name TEXT NOT NULL,\n" +
                "password TEXT NOT NULL\n" +
                ");";
        connection.createStatement().executeUpdate(createTableSql);
        System.out.println("utworzono tabele");
    }
    public void execStatementForZad4a() throws SQLException {
        String createTableSql = "CREATE TABLE shop_product (\n" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "productName TEXT NOT NULL,\n" +
                "price INTEGER NOT NULL\n" +
                ");";
        connection.createStatement().executeUpdate(createTableSql);
        System.out.println("utworzono tabele");
    }
    public void execStatementForZad4b() throws SQLException {
        String createTableSql = "CREATE TABLE shop_warehouse (\n" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "amount INTEGER NOT NULL\n" +
                ");";
        connection.createStatement().executeUpdate(createTableSql);
        System.out.println("utworzono tabele");
    }
    public void deleteProduct(int id) {
        try {
            Statement statement = connection.createStatement();
            String deleteQuery = "DELETE FROM shop_warehouse WHERE id = " + id;
            int rowsDeleted = statement.executeUpdate(deleteQuery);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
