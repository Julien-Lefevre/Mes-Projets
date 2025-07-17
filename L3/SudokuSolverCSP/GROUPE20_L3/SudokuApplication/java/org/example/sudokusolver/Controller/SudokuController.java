package org.example.sudokusolver.Controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.example.sudokusolver.SudokuSolveur;
import org.example.sudokusolver.Model.SudokuModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.UnaryOperator;

public class SudokuController {

    @FXML
    GridPane sudokuGrid;
    @FXML
    GridPane DigitGrid;
    @FXML
    Button DeleteButton;
    @FXML
    VBox bottomVbox;
    @FXML
    Label errormessage;


    TextField selectedCase = null;
    String selectedInt;

    private SudokuModel mysudoku = new SudokuModel();

    public void initialize(){

        initSudokuGridTextfield();

        initDigitPadButton();

        //action boutton delete
        DeleteButton.setOnMouseClicked(event -> {
            if (selectedCase != null) {
                selectedCase.setText("");
            }
        });
    }


    /**
     * Cette méthode permet de vider les valeurs affichées dans la grille.
      */
    @FXML
    public void resetthegrid()
    {
        mysudoku.resetGrid();
        for(Node child : sudokuGrid.getChildren()){
            if (child instanceof TextField)
                ((TextField)child).setText("");
        }
        this.errormessage.setStyle("-fx-opacity: 0");
    }


    /**
     * Cette méthode lance la résolution de la grille, la fenêtre se ferme si une solution est trouvée, et une fenêtre avec le résultat s'ouvre. Sinon, un message indique qu'il n'existe aucune solution.
     */
    @FXML
    public void solvethegrid() {
        for (Node child : sudokuGrid.getChildren()) {
            if (child instanceof TextField) {
                String value = ((TextField) child).getText();
                if (value != null && !value.equals(""))
                    mysudoku.modifVariable(GridPane.getRowIndex(child), GridPane.getColumnIndex(child), Integer.parseInt(value));
            }
        }
        ArrayList<Integer> results = mysudoku.lauchSolve();
        if (!results.isEmpty()) {
            ResultSudokuController.theresults = results;
            try {
                SudokuSolveur.start2();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            mysudoku.resetGrid();
            this.errormessage.setStyle("-fx-text-fill: red; -fx-font-size: 20pt;-fx-opacity: 100");
        }
    }

    /**
     * Initialise la grille de sudoku avec des Textfields pouvant contenir seulement des chiffres
     */
    public void initSudokuGridTextfield(){
        // Création d'un UnaryOperator pour filtrer les entrées
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[1-9]?")) { // Vérifie si la nouvelle valeur est entre 1 et 9
                return change;
            }
            return null; // Rejette le changement s'il ne correspond pas au modèle
        };

        //action et crétion Textfield Grille sudoku
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextFormatter<String> textFormatter = new TextFormatter<>(filter);
                TextField t1 = new TextField();
                t1.setTextFormatter(textFormatter);
                t1.setStyle("-fx-background-color: transparent");
                sudokuGrid.add(t1, i, j);
                final int x = i;
                final int y = j;
                t1.setOnMouseClicked(event ->{
                    selectedCase = (TextField) event.getSource();
                });

                t1.setOnKeyPressed(event -> {
                    String keyPressed = event.getText();
                    if(keyPressed.matches("[1-9]?")){

                    }
                });


            }
        }
    }

    /**
     * initialise les bouttons contenant Digits pour remplir la grille
     */
    public void initDigitPadButton(){
        for (Node child : DigitGrid.getChildren()) {
            child.setOnMouseClicked(event -> {
                Button clickedButton = (Button) event.getSource();

                if (selectedCase != null) {
                    selectedCase.setText(clickedButton.getText());
                }

            });
        }
    }
}