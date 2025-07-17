package CLASS.Room;

import CLASS.Item.*;
import CLASS.Door.*;
import CLASS.Character.*;
import java.util.*;

public class Room{
    private final String name;
    private final String description;
    private ArrayList <Item> items;
    private ArrayList <Human> people;
    protected Map<String, Door> Exits;

    public Room(String name, String description){                                               //default constructors for Instantiating Room
        this.name = name;
        this.description = description;
        this.items = new ArrayList<Item>();
        this.people = new ArrayList<Human>();
        this.Exits = new HashMap<>();
    }

    public String getName() { return this.name; }                                               //method that return the Name of the Room
    public ArrayList<Item> getItems(){ return items; }                                          //method that return the list of people in the Room
    public ArrayList<Human> getPeople(){ return people; }
    public String getDescription(){
        String s = "\n";
        if (this.items.size() != 0){
            s += " I can see item(s): " + this.items.get(0).getName();
            for (int item = 1; item < this.items.size(); item++){
                s +=  ", " + this.items.get(item).getName();
            }
            s += ".\n";
        }
        if (this.people.size() != 0){
            s += " I'm  not alone here, there is: " + this.people.get(0).getName();
            for (int person = 1; person < this.people.size(); person++){
                s +=  ", " + this.people.get(person).getName();
            }
            s += ".\n";
        }
        return this.description + s;
    }

    public void addHuman(Human human) {this.people.add(human);}                                 //method that add a human in the Room

    public void addItem(Item item) { this.items.add(item);}                                     //method that add a item in the Room

    public Map<String, Door> getExits() {return Exits;}                                         //method that return all the Exits of a room

    public void addExit(Room room, Door door){                                                  //method that add a Exit(<name of the room, Door>) to the list of Map Exits
        Exits.put(room.getName(), door);
    }

    public void rmPeopleFromRoom(Human h){                                                      //method that remove a people in the room from the room
        boolean isIn = false;
        if (this.people.size() != 0){
            for(Human person : this.people ){
                if (h.equals(person)){
                    isIn = true;
                }
            }
        }
        if (isIn) { this.people.remove(h); }
    }


}