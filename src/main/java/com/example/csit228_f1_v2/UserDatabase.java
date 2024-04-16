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
            System.out.println("DB Connection success!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return c;
    }

    public void createUser(String username, String password) {
        try (Connection c = getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "INSERT INTO users (username, password) VALUES (?, ?)"
             )) {
            statement.setString(1, username);
            statement.setString(2, password);
            int rowsInserted = statement.executeUpdate();
            System.out.println("Rows inserted: " + rowsInserted);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean getUsers(String username, String password) {
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

    public void changePassword(String oldPasword, String newPassword) {
        try (Connection c = getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "UPDATE users SET name=? WHERE id=?"
             )) {
            String new_name = "Cherry Lyn Beifong";
            int id = 2;
            statement.setString(1, new_name);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            System.out.println("Rows Updated: " + rowsUpdated);
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
            System.out.println("Rows deleted: " + rowsDeleted);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
