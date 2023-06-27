package pl.umcs.oop.lec7.auth;

import at.favre.lib.crypto.bcrypt.BCrypt;
import database.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AccountManager {
    DatabaseConnection connection;
    public AccountManager(DatabaseConnection connection) {
        this.connection = connection;
    }

    public int register(String userName, String userPassword) throws SQLException {
        PreparedStatement statement = connection.getConnection()
                .prepareStatement("SELECT * FROM auth_account WHERE name = ?;");
        statement.setString(1, userName);

        statement.execute();
        if (statement.getResultSet().next())
            throw new RuntimeException("User already exists.");

        statement = connection.getConnection().prepareStatement("INSERT INTO auth_account(name, password) VALUES (?, ?);");
        statement.setString(1, userName);
        statement.setString(2, BCrypt.withDefaults().hashToString(12, userPassword.toCharArray()));
        statement.executeUpdate();

        ResultSet resultSet = statement.getGeneratedKeys();
        resultSet.next();
        return resultSet.getInt(1);
    }

    public Account getUser(String userName) throws SQLException {
        PreparedStatement statement = connection.getConnection()
                .prepareStatement("SELECT id, name FROM person WHERE name = ?;");
        statement.setString(1, userName);
        statement.execute();
        ResultSet result = statement.getResultSet();
        if (result.next()) {
            return new Account(result.getString("id"), userName);
        }
        throw new RuntimeException("User was not found in database");
    }

    public boolean login(String userName, String userPassword) throws SQLException {
        PreparedStatement statement = connection.getConnection()
                .prepareStatement("SELECT userPassword FROM auth_account WHERE name = ?;");

        statement.setString(1, userName);
        statement.execute();

        ResultSet result = statement.getResultSet();
        if (result.next()) {
            String hashedPassword = result.getString("password");
            BCrypt.Result cryptResult = BCrypt.verifyer().verify(userPassword.toCharArray(), hashedPassword);
            return cryptResult.verified;
        } else
            throw new RuntimeException("User was not found in database");
    }
}
