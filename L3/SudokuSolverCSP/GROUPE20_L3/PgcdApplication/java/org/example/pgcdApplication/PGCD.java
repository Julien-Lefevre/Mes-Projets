package org.example.pgcdApplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PGCD extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PGCD.class.getResource("maquette_pgcd.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 625, 400);
        stage.setTitle("PGCD");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}