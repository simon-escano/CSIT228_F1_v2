package com.example.csit228_f1_v2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends javafx.application.Application {
    public static User user;
    public static Database db;
    public static Stage stage;
    public static Scrib selectedScrib;
    @Override
    public void start(Stage stage) {
        Main.stage = stage;
        Main.stage.setTitle("Scrib");
        db = new Database("jdbc:mysql://localhost:3306/dbcsit228f1_escano", "root", "");
        load("loginpage.fxml", stage);
    }

    public static void main(String[] args) {
        launch();
    }

    public static void load(String fxml, Stage stage) {
        try {
            Parent p = FXMLLoader.load(Main.class.getResource(fxml));
            Scene s = new Scene(p);
            stage.setScene(s);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}