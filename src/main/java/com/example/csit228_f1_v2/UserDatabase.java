package com.example.csit228_f1_v2;

import java.sql.*;

public class UserDatabase {
    public static String URL;
    public static String USERNAME;
    public static String PASSWORD;

    public UserDatabase(String URL, String USERNAME, String PASSWORD) {
        this.URL = URL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    public Connection getConnection() {
        Connection c;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return c;
    }

    public boolean createUser(String username, String password) {
        try (Connection c = getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "INSERT INTO users (username, password) VALUES (?, ?)"
             )) {
            String query = "SELECT * FROM users WHERE username='" + username + "'";
            ResultSet res = statement.executeQuery(query);
            if (!res.next()) {
                statement.setString(1, username);
                statement.setString(2, password);
                int rowsInserted = statement.executeUpdate();
                System.out.println("Successfully created user. Rows inserted: " + rowsInserted);
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean logIn(String username, String password) {
        try (Connection c = getConnection();
             Statement statement = c.createStatement()) {
            String query = "SELECT * FROM users";
            ResultSet res = statement.executeQuery(query);
            while(res.next()) {
                String dbusername = res.getString("username");
                String dbpassword = res.getString("password");
                if (dbusername.equals(username) && dbpassword.equals(password)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void changePassword(String username, String password) {
        try (Connection c = getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "UPDATE users SET password=? WHERE username=?"
             )) {
            statement.setString(1, password);
            statement.setString(2, username);
            int rowsUpdated = statement.executeUpdate();
            System.out.println("Successfully changed password. Rows Updated: " + rowsUpdated);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(String username, String password) {
        try (Connection c = getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "DELETE FROM users WHERE username = ? AND password = ?"
             )) {
            statement.setString(1, username);
            statement.setString(2, password);
            int rowsDeleted = statement.executeUpdate();
            System.out.println("Successfully deleted user. Rows deleted: " + rowsDeleted);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
