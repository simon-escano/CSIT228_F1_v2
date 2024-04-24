package com.example.csit228_f1_v2;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;


import java.io.IOException;
import java.util.ArrayList;

public class HomePage {
    public Label lblUsername;
    public Button btnCreate;
    public MenuItem btnLogOut;
    public MenuItem btnChangePassword;
    public MenuItem btnDeleteAccount;

    public VBox[] parts = new VBox[4];
    public int numOfParts = 4;
    @FXML
    public VBox notes_partition_0;
    @FXML
    public VBox notes_partition_1;
    @FXML
    public VBox notes_partition_2;
    @FXML
    public VBox notes_partition_3;


        @FXML
        public void initialize() {
            lblUsername.setText(Main.user.username + "!");
            btnCreate.setOnAction(e -> Main.load("createpage.fxml", Main.stage));
            btnLogOut.setOnAction(e -> logOut());
            btnChangePassword.setOnAction(e -> Main.load("changepasswordpage.fxml", Main.stage));
            btnDeleteAccount.setOnAction(e -> deleteAccount());

            parts = new VBox[numOfParts];
            parts[0] = notes_partition_0;
            parts[1] = notes_partition_1;
            parts[2] = notes_partition_2;
            parts[3] = notes_partition_3;

            ArrayList<Scrib> scribs = Main.db.getScribs(Main.user.id);
            for (int i = 0; i < scribs.size(); i++) {
                parts[i % parts.length].getChildren().add(scribs.get(i));
            }
        }

    public void logOut() {
        Main.user = null;
        try {
            Parent p = FXMLLoader.load(getClass().getResource("loginpage.fxml"));
            Scene s = new Scene(p);
            Main.stage.setScene(s);
            Main.stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount() {
        Main.db.deleteUser(Main.user.id);
        Main.user = null;
        Main.load("loginpage.fxml", Main.stage);
    }
}
