package CLASS.Item;

import CLASS.Game.*;

import java.util.Scanner;

public class Diary extends Item {
    private String content;

    public Diary(String name, String description ){             //default constructors for Instantiating Diairy
        super(name, description);
        this.content = "";
    }

    public String getContent(){ return this.content; }            //get String content from the private attribute content
    
    public void use(Scanner scan){                              //method use which add a user input in the Diary 
        System.out.println(this.getContent());
        String comand = MyScanner.scanTxt(scan);
        this.content = this.content + "\n" + comand;
    }
        
}
