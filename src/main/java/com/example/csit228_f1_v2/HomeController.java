package com.example.csit228_f1_v2;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class HomeController {
    public ToggleButton tbNight;
    public TextField passwordInput;

    public void onNightModeClick() {
        if (tbNight.isSelected()) {
            tbNight.getParent().setStyle("-fx-background-color: BLACK");
            tbNight.setText("DAY");
        } else {
            tbNight.getParent().setStyle("-fx-background-color: WHITE");
            tbNight.setText("NIGHT");
        }
    }

    public void onLogoutClick() {
        HelloApplication.currentUsername = null;
        HelloApplication.drawComponents(HelloApplication.stage);
    }
    public void onChangePasswordClick() {
        String passInput = passwordInput.getText();
        if (passInput.isEmpty()) {
            System.out.println("The change password field should be filled.");
        } else {
            HelloApplication.users.changePassword(HelloApplication.currentUsername, passInput);
        }
    }

    public void onDeleteAccountClick() {
        HelloApplication.users.deleteUser(HelloApplication.currentUsername, HelloApplication.currentPassword);
        HelloApplication.currentUsername = null;
        HelloApplication.drawComponents(HelloApplication.stage);
    }
}
