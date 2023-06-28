import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection implements pl.umcs.oop.lec7.database.DatabaseConnection {
    private Connection connection;

    public void connect(String path){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+path);
            System.out.println("Connected");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void disconnect(){
        try {
            connection.close();
            System.out.println("Disconnected");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
