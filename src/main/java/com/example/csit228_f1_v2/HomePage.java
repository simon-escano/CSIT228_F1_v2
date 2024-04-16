package com.example.csit228_f1_v2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;

public class HomePage {
    public ToggleButton tbNight;
    public TextField passwordInput;
    public Label lblError;

    public void onNightModeClick() {
        if (tbNight.isSelected()) {
            tbNight.getParent().setStyle("-fx-background-color: WHITE");
            tbNight.setText("DAY");
        } else {
            tbNight.getParent().setStyle("-fx-background-color: BLACK");
            tbNight.setText("NIGHT");
        }
    }

    public void logOut() {
        Application.user = null;
        try {
            Parent p = FXMLLoader.load(getClass().getResource("loginpage.fxml"));
            Scene s = new Scene(p);
            Application.stage.setScene(s);
            Application.stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void changePassword() {
        String passInput = passwordInput.getText();
        if (passInput.isEmpty()) {
            lblError.setText("The change password field should be filled.");
        } else {
            Application.users.changePassword(Application.user.username, passInput);
            lblError.setText("Successfully changed password.");
        }
    }

    public void deleteAccount() {
        Application.users.deleteUser(Application.user.username, Application.user.password);
        Application.user = null;
        try {
            Parent p = FXMLLoader.load(getClass().getResource("loginpage.fxml"));
            Scene s = new Scene(p);
            Application.stage.setScene(s);
            Application.stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
