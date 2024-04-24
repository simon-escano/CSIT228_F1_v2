package com.example.csit228_f1_v2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreatePage {
    @FXML
    TextField tfTitle;
    @FXML
    TextArea taBody;
    @FXML
    Button btnCreate;
    @FXML
    Button btnCancel;
    @FXML
    public void initialize() {
        btnCreate.setOnAction(e -> create(tfTitle.getText(), taBody.getText()));
        btnCancel.setOnAction(e -> {
            Main.load("homepage.fxml", Main.stage);
        });
    }

    public void create(String title, String body) {
        Main.db.createScrib(Main.user.id, title, body);
        Main.load("homepage.fxml", Main.stage);
    }
}
