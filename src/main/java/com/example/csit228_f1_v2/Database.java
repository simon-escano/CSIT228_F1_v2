package com.example.csit228_f1_v2;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    public String URL;
    public String USERNAME;
    public String PASSWORD;

    public Database(String URL, String USERNAME, String PASSWORD) {
        this.URL = URL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;

        try (Connection c = getConnection();
             Statement statement = c.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(50) NOT NULL, password VARCHAR(50) NOT NULL)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS scribs (id INT PRIMARY KEY AUTO_INCREMENT, userid INT NOT NULL, title VARCHAR(255) NOT NULL, body VARCHAR(1000) NOT NULL, FOREIGN KEY (userid) REFERENCES users(id))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public int logIn(String username, String password) {
        try (Connection c = getConnection();
             Statement statement = c.createStatement()) {
            String query = "SELECT id FROM users WHERE username='" + username + "' AND password='" + password + "'";
            ResultSet res = statement.executeQuery(query);
            if (res.next()) {
                return res.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    public void changePassword(int id, String password) {
        try (Connection c = getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "UPDATE users SET password=? WHERE id=?"
             )) {
            statement.setString(1, password);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            System.out.println("Successfully changed password. Rows Updated: " + rowsUpdated);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(int id) {
        try (Connection c = getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "DELETE FROM users WHERE id=?"
             )) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            System.out.println("Successfully deleted user. Rows deleted: " + rowsDeleted);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createScrib(int userid, String title, String body) {
        try (Connection c = getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "INSERT INTO scribs (userid, title, body) VALUES (?, ?, ?)"
             )) {
            statement.setInt(1, userid);
            statement.setString(2, title);
            statement.setString(3, body);
            int rowsInserted = statement.executeUpdate();
            System.out.println("Successfully created Scrib. Rows inserted: " + rowsInserted);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editScrib(int id, String title, String body) {
        try (Connection c = getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "UPDATE scribs SET title=?, body=? WHERE id=?"
             )) {
            statement.setString(1, title);
            statement.setString(2, body);
            statement.setInt(3, id);
            int rowsUpdated = statement.executeUpdate();
            System.out.println("Successfully edited Scrib. Rows Updated: " + rowsUpdated);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteScrib(int id) {
        try (Connection c = getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "DELETE FROM scribs WHERE id=?"
             )) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            System.out.println("Successfully deleted Scrib. Rows deleted: " + rowsDeleted);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Scrib> getScribs(int userid) {
        ArrayList<Scrib> scribs = new ArrayList<>();
        try (Connection c = getConnection();
             Statement statement = c.createStatement();
             ResultSet res = statement.executeQuery("SELECT id, title, body FROM scribs WHERE userid=" + userid)) {
            while (res.next()) {
                int id = res.getInt("id");
                String title = res.getString("title");
                String body = res.getString("body");
                scribs.add(new Scrib(id, title, body));
            }
            return scribs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
