package com.example.csit228_f1_v2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginPage {
    public Label lblError;
    public TextField tfUsername;
    public PasswordField pfPassword;
    public Button btnRegister;
    public Button btnLogin;

    public void register() {
        String username = tfUsername.getText();
        String password = pfPassword.getText();
        if (username.isEmpty() || password.isEmpty()) {
            lblError.setText("Both fields should be filled.");
            return;
        }

        if (!Application.users.createUser(username, password)) {
            lblError.setText("Someone already has that username.");
        } else {
            lblError.setText("User " + username + " successfully created");
        }
    }

    public void login() {
        String username = tfUsername.getText();
        String password = pfPassword.getText();
        if (username.isEmpty() || password.isEmpty()) {
            lblError.setText("Both fields should be filled.");
            return;
        }

        if (Application.users.logIn(username, password)) {
            Application.user = new User(username, password);
            try {
                Parent p = FXMLLoader.load(getClass().getResource("homepage.fxml"));
                Scene s = new Scene(p);
                Application.stage.setScene(s);
                Application.stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            lblError.setText("Incorrect username or password.");
        }
    }
}