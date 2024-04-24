package com.example.csit228_f1_v2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginPage {
    public Label lblError;
    public TextField tfUsername;
    public PasswordField pfPassword;
    public Button btnRegister;
    public Button btnLogin;

    @FXML
    public void initialize() {
        btnRegister.setOnAction(e -> register(tfUsername.getText(), pfPassword.getText()));
        btnLogin.setOnAction(e -> login(tfUsername.getText(), pfPassword.getText()));
    }

    public void register(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            lblError.setText("Both fields should be filled.");
            return;
        }

        if (!Main.db.createUser(username, password)) {
            lblError.setText("Someone already has that username.");
        } else {
            lblError.setText("User " + username + " successfully created");
        }
    }

    public void login(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            lblError.setText("Both fields should be filled.");
            return;
        }

        int id = Main.db.logIn(username, password);
        if (id != -1) {
            Main.user = new User(id, username, password);
            Main.load("homepage.fxml", Main.stage);
        } else {
            lblError.setText("Incorrect username or password.");
        }
    }
}