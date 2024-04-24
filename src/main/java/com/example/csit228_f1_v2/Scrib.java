package com.example.csit228_f1_v2;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;

public class Scrib extends VBox {
    int id;
    String title;
    String body;

    public Scrib(int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;

        this.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        this.setSpacing(8.0);
        this.setStyle("-fx-border-width: 1px; -fx-border-color: #5F6368; -fx-border-radius: 8px;");

        Label titleLabel = new Label(title);
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setWrapText(true);
        titleLabel.setFont(Font.font("System Bold", FontWeight.BOLD, 22.0));

        Label bodyLabel = new Label(body);
        bodyLabel.setTextFill(Color.WHITE);
        bodyLabel.setWrapText(true);
        bodyLabel.setFont(Font.font(16.0));

        Button editButton = new Button("Edit Scrib");
        editButton.setMaxWidth(Double.MAX_VALUE);
        editButton.setMnemonicParsing(false);
        editButton.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-border-color: #66abff; -fx-border-radius: 4;");
        editButton.setTextFill(Color.valueOf("#66abff"));
        editButton.setCursor(Cursor.HAND);
        editButton.setOnAction(e -> {
            Main.selectedScrib = this;
            Main.load("editscribpage.fxml", Main.stage);
        });


        Button deleteButton = new Button("Delete Scrib");
        deleteButton.setMaxWidth(Double.MAX_VALUE);
        deleteButton.setMnemonicParsing(false);
        deleteButton.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-border-color: #d36a6a; -fx-border-radius: 4;");
        deleteButton.setTextFill(Color.valueOf("#d36a6a"));
        deleteButton.setCursor(Cursor.HAND);
        deleteButton.setOnAction(e -> {
            Main.db.deleteScrib(id);
            Main.load("homepage.fxml", Main.stage);
        });

        this.getChildren().addAll(titleLabel, bodyLabel, editButton, deleteButton);
        this.setPadding(new Insets(16.0));
    }
}