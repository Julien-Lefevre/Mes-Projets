package org.example.pgcdApplication.Model;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class ValueField extends HBox {
    public static ArrayList<ValueField> valuesList = new ArrayList<>();
    private int index;
    private Label labVal = new Label();
    private TextField tfVal = new TextField();
    private Button delVal = new Button("Delete Value");

    public ValueField(){
        super();
        this.index = valuesList.size();
        this.labVal.setText("Value n°" + (this.index + 3) + " : ");
        this.tfVal.setPromptText("Enter a value...");
        tfVal.setMaxWidth(125);
        this.getChildren().addAll(this.labVal, this.tfVal, this.delVal);
        setSpacing(10);
        valuesList.add(this);
    }

    public void setIndex(int i){
        this.index = i;
    }

    public void setLabVal(int index){
        this.labVal.setText("Value n°" + (index + 3) + " : ");
    }

    public int getTFvalue(){
        return Integer.parseInt(this.tfVal.getText());
    }

    public Button getDelVal(){
        return this.delVal;
    }

    public TextField getTf(){return this.tfVal;}

    public void deleteValue(){
        valuesList.remove(this);
        for (int i = this.index; i < valuesList.size(); i++){
            valuesList.get(i).setIndex(i);
            valuesList.get(i).setLabVal(i);
        }
    }

    public void resetTF(){
        this.tfVal.clear();
        this.tfVal.setPromptText("Enter a value...");
    }

}
