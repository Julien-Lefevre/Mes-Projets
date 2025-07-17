
package CLASS.Item;

import CLASS.Character.*;
import CLASS.Game.TheGame;

import java.util.Scanner;

public class Item{
    private final String name;
    private final String description;
    private TheGame game = TheGame.getInstance();

    public Item(String name, String description){                                           //default constructors for Instantiating Item
        this.name = name;
        this.description = description;
    }

    public String getName(){return this.name;}                                              //get String name from the private attribute name
    public String getDescription(){return this.description;}
                                    //get String description from the private attribute description
    public boolean createFakeBeard(Item item1, Item item2) {                                //method that return if the creation of a fake beard is possible with what the Hero has in his inventory
        if (item1.getName().equals("Glue") && item2.getName().equals("Hair")){
            return true;
        }
        if (item2.getName().equals("Glue") && item1.getName().equals("Hair")){
            return true;
        }
        return false;
     }
    public boolean createSandwich(Item item1, Item item2) {                                 //method that return if the creation of a sandwich is possible with what the Hero has in his inventory
        if (item1.getName().equals("Bread") && item2.getName().equals("Ingredients")){
            return true;
        }
        if (item2.getName().equals("Bread") && item1.getName().equals("Ingredients")){
            return true;
        }
        return false;
     }

    public void use(Hero hero){                                                             //method that allow the Hero or the user to use a item to equip it 
        if (this.getName().equals("FakeBeard")){
            hero.setHasFakeBeard();
            if (hero.getHasFakeBeard()) { System.out.println("You're wearing the FakeBeard"); }
            else                        { System.out.println("You're not wearing the FakeBeard anymore"); }
            }
        else if (this.getName().equals("ChalkPowder") && hero.getRoom().getName().equals("FirstFloor"))     { System.out.println("You see the digit 1, 2, 3 and 9 becoming white"); }
        else if (this.getName().equals("FireCracker") && hero.getRoom().getName().equals("FirstFloor")){
            game.Principal.setRoom(hero.getRoom());
            game.FirstFloor.addHuman(game.Principal);
            System.out.println("You hear a loud bang and then see the principal coming here angry");
        }
        else if (this.getName().equals("ExamSubject")) {
            System.out.println("Congratulation! You've reach the goal of the game, but have you done it in all the possible ways?");
            hero.quit();
        }
        else { System.out.println("I can't use that here."); }
    }

    public void use(Hero hero, Item item1, Item item2){                                     //method that allow the Hero or the user to combine two item to create a new one(the two first item are destro) 
            if (createFakeBeard(item1, item2)){
            hero.remFromBag(item1);
            hero.remFromBag(item2);
            System.out.println("Glue and Hair used to create FakeBeard!");
            Item FakeBeard = new Item("FakeBeard", "This should fool any adult!");
            hero.addInBag(FakeBeard);
        }
        else if (createSandwich(item1, item2)){
            hero.remFromBag(item1);
            hero.remFromBag(item2);
            System.out.println(item1.getName() + " and " + item2.getName() + " used to create Sandwich!");
            Item Sandwich = new Item("Sandwich", "This should fill any adult!");
            hero.addInBag(Sandwich);
        }
        else { System.out.println("Wrong combination :("); }
    }

    public void use(Scanner scan){}                                                         //method that is used for the diary to write in it with the user input
}