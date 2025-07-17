package org.example.sudokusolver;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SudokuSolveur extends Application {

    private static Stage stageencours;

    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("maquette_pgcd.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(SudokuSolveur.class.getResource("sudoku.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("SUDOKU");
        stage.setScene(scene);
        stageencours = stage;
        stage.show();
    }

    public static void start2() throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("maquette_pgcd.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(SudokuSolveur.class.getResource("resultsudoku.fxml"));
        stageencours.close();
        Stage newstage = new Stage();
        try {
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            newstage.setTitle("SUDOKU");
            newstage.setScene(scene);
            newstage.show();
            stageencours = newstage;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public static void start3() throws IOException {
            //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("maquette_pgcd.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(SudokuSolveur.class.getResource("sudoku.fxml"));
            stageencours.close();
            Stage newstage = new Stage();
            try {
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                newstage.setTitle("SUDOKU");
                newstage.setScene(scene);
                newstage.show();
                stageencours=newstage;
            } catch (IOException e) {
                e.printStackTrace();
            }
    }


    public static void main(String[] args) {
        launch();
    }
}