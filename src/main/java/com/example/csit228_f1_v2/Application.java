package com.example.csit228_f1_v2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    public static User user;
    public static UserDatabase users;
    public static Stage stage;
    @Override
    public void start(Stage stage) {
        Application.stage = stage;
        user = null;
        users = new UserDatabase("jdbc:mysql://localhost:3306/dbcsit228f1_escano", "root", "");
        try {
            Parent p = FXMLLoader.load(getClass().getResource("loginpage.fxml"));
            Scene s = new Scene(p);
            stage.setScene(s);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}