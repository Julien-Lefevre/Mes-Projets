package org.example.pgcdApplication.Controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.pgcdApplication.Model.ValueField;
import solver.solvercsp.Contraintes.Binaire.XequalsY;
import solver.solvercsp.IntDomaine;
import solver.solvercsp.SolverCSP;
import solver.solvercsp.Variable;

import java.util.ArrayList;

public class PgcdController {
    @FXML
    Label titre;
    @FXML
    VBox allValues;
    @FXML
    TextField value1;
    @FXML
    TextField value2;
    @FXML
    Button addValue;
    @FXML
    HBox addValueVbox;
    @FXML
    VBox VBoxResult;
    @FXML
    Button solve;
    @FXML
    Button reset;
    @FXML
    Label result;
    SolverCSP solver;

    int compteurVar = 2;

    public void initialize(){
        titre.setPadding(new Insets(10,0,10,75));
        allValues.setSpacing(10);
        allValues.setPadding(new Insets(10,10,10,30));
        addValueVbox.setPadding(new Insets(5,0,0,30));
        VBoxResult.setPadding(new Insets(10,30,10,0));
        value1.setMaxWidth(125);
        value2.setMaxWidth(125);
    }

    public void deleteValue(ValueField val){
        val.deleteValue();
        allValues.getChildren().remove(val);
        this.compteurVar -= 1;
        if(this.compteurVar == 7){
            addValue.setDisable(false);
        }
    }

    @FXML
    public void addValueAction(){
        if(this.compteurVar < 8){
            ValueField valueField = new ValueField();
            allValues.getChildren().add(valueField);
            valueField.getDelVal().setOnAction(e->{
                deleteValue(valueField);
            });
            compteurVar += 1;
            if (this.compteurVar == 8){
                addValue.setDisable(true);
            }
        }
    }

    public boolean createVars(){
        boolean filtre = true;
        ArrayList<Integer> domaine1 = new ArrayList<>();
        ArrayList<Integer> domaine2 = new ArrayList<>();
        int val1 = -1;
        int val2 = -1;
        try {
            val1 = Integer.parseInt(this.value1.getText());
        } catch (NumberFormatException e){
            System.out.println("erreur: la valeur n'est pas un entier");
            value1.setPromptText("incorrect value");
            value1.setText("");
            value1.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red");
            value1.setOnMouseClicked(event -> {value1.setPromptText("Enter a value..."); value1.setStyle(null);});
            filtre = false;
        }
        try {
            val2 = Integer.parseInt(this.value2.getText());
        } catch (NumberFormatException e){
            System.out.println("erreur: la valeur n'est pas un entier");
            value2.setPromptText("incorrect value");
            value2.setText("");
            value2.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red");
            value2.setOnMouseClicked(event -> {value2.setPromptText("Enter a value..."); value2.setStyle(null);});
            filtre = false;
        }
        for (int div = val1; div > 0; div--){     //ajouter value1 et value2 et arnaud verif si c'est du int
            if (val1 % div == 0){
                domaine1.add(div);
                domaine1.add(div);
            }
        }
        solver.addVariable(new Variable("var0", new IntDomaine(domaine1)));

        for (int div = val2; div > 0; div--){     //ajouter value1 et value2 et arnaud verif si c'est du int
            if (val2 % div == 0){
                domaine2.add(div);
                domaine2.add(div);
            }
        }
        solver.addVariable(new Variable("var1", new IntDomaine(domaine2)));

        for (int i = 0; i < ValueField.valuesList.size(); i++){
            ArrayList<Integer> domaine = new ArrayList<>();
            ValueField vf = ValueField.valuesList.get(i);
            try {
                for (int div = vf.getTFvalue(); div >0; div--){
                    if (vf.getTFvalue() % div == 0){
                        domaine.add(div);
                        domaine.add(div);
                    }
                }
                solver.addVariable(new Variable("var" + (i + 2), new IntDomaine(domaine)));
            } catch (NumberFormatException e){
                System.out.println("erreur: la valeur n'est pas un entier");
                TextField tf = vf.getTf();
                tf.setText("");
                tf.setPromptText("incorrect value");
                tf.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red");
                tf.setOnMouseClicked(event -> {tf.setPromptText("Enter a value..."); tf.setStyle(null);});

                filtre = false;
            }
        }
        return filtre;
    }
    public void createConts(){
        int var1 = 1;
        for (int var2 = 2; var2 < solver.getCompteurVar(); var2++){
                XequalsY cont = new XequalsY(solver.getVar(var1), solver.getVar(var2));
                solver.addContrainte(cont);
        }
    }
    @FXML
    public void solveAction(){
        ArrayList<Integer> test;
        this.solver = new SolverCSP(false);
        if(createVars()){
            createConts();
            test = solver.evaluate();
            System.out.println(test);
            this.result.setText(String.valueOf(test.get(0)));
        }
    }
    @FXML
    public void resetAction(){
        value1.clear();
        value1.setPromptText("Enter a value...");
        value2.clear();
        value2.setPromptText("Enter a value...");
        for(int i = ValueField.valuesList.size() - 1; i >= 0; i--){
            deleteValue(ValueField.valuesList.get(i));
        }
        result.setText("");
    }
}
