package com.example.csit228_f1_v2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ChangePasswordPage {
    @FXML
    Label lblError;
    @FXML
    TextField tfOldPassword;
    @FXML
    TextField tfNewPassword;
    @FXML
    Button btnChange;
    @FXML
    Button btnCancel;
    @FXML
    public void initialize() {
        btnChange.setOnAction(e -> changePassword(tfOldPassword.getText(), tfNewPassword.getText()));
        btnCancel.setOnAction(e -> {
            Main.load("homepage.fxml", Main.stage);
        });
    }

    public void changePassword(String oldPassword, String newPassword) {
        if (oldPassword.isEmpty() || newPassword.isEmpty()) {
            lblError.setText("Both fields should be filled.");
            return;
        }

        if (!oldPassword.equals(Main.user.password)) {
            lblError.setText("The old password you entered is incorrect.");
            return;
        }


        if (oldPassword.equals(newPassword)) {
            lblError.setText("New password cannot be the same as old password.");
            return;
        }

        Main.db.changePassword(Main.user.id, newPassword);
        Main.load("homepage.fxml", Main.stage);
    }
}
