package com.example.csit228_f1_v2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EditScribPage {
    @FXML
    public TextField tfTitle;
    @FXML
    public TextArea taBody;
    @FXML
    public Button btnSave;
    @FXML
    public Button btnCancel;
    @FXML
    public void initialize() {
        btnSave.setOnAction(e -> save(Main.selectedScrib.id, tfTitle.getText(), taBody.getText()));
        btnCancel.setOnAction(e -> {
            Main.load("homepage.fxml", Main.stage);
        });
        tfTitle.setText(Main.selectedScrib.title);
        taBody.setText(Main.selectedScrib.body);
    }

    public void save(int id, String title, String body) {
        Main.db.editScrib(id, title, body);
        Main.load("homepage.fxml", Main.stage);
    }
}
