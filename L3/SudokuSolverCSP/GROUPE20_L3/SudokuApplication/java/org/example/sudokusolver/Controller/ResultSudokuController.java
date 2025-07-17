package org.example.sudokusolver.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.example.sudokusolver.SudokuSolveur;

import java.io.IOException;
import java.util.ArrayList;

public class ResultSudokuController {
    @FXML
    GridPane sudokuGrid;

    public static ArrayList<Integer> theresults = new ArrayList<>();

    /**
     * Cette méthode affiche dans chaque case la solution trouvée
     */
    public void initialize() {
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                Label lab = new Label(String.valueOf(theresults.get(0)));
                lab.setStyle("-fx-font-size: 12pt");
                GridPane.setHalignment(lab, javafx.geometry.HPos.CENTER);
                GridPane.setValignment(lab, javafx.geometry.VPos.CENTER);
                theresults.remove(0);
                this.sudokuGrid.add(lab, j, i);
            }
        }
    }


    /**
     * Cette méthode lance une nouvelle fenêtre afin de saisir une nouvelle grille. La fenêtre de résultats se ferme.
     */
    @FXML
    public void thenewgrid()
    {
        try {
            SudokuSolveur.start3();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
