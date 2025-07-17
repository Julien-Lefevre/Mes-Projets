package CLASS.Character;

import CLASS.Room.*;
import CLASS.Item.*;
import java.util.*;

 public abstract class Human {

    private final String name;
    private String description;
    private int HP;
    protected Room room;
    private ArrayList<Item> bag;
    private boolean isUnconscious;

    public Human(String name, String description, int HPmax, Room room){
        this.name = name;
        this.description = description;
        this.HP = HPmax;
        this.room = room;
        this.bag = new ArrayList<Item>();
        this.isUnconscious = false;
    }

    public String getName(){ return this.name; }
    public String getDescription(){ return this.description; }
    public int getHP(){ return this.HP; }
    public Room getRoom(){ return this.room; }
    public ArrayList<Item> getBag(){ return this.bag; }
    public boolean getIsUnconscious(){ return this.isUnconscious; }
    public void addInBag(Item item){ this.bag.add(item); }
    public void remFromBag(Item item){ this.bag.remove(item); }
    public void setRoom(Room room) { this.room = room; }
    public void setDescription(String description) { this.description = description; }
    public void setHP(int HP) { this.HP = HP; }
    public void printInBag(){
        for (int i = 0; i < this.bag.size(); i++){
            System.out.println(bag.get(i).getName());
        }
    }

    public Boolean searchInBag(String item){
        for (Item inBag : this.bag){
            if (inBag.getName().equals(item)){
                return true;
            }
        }
        return false;
    }

    public void sethumanInRoom(){
        room.addHuman(this);
    }

    public void give(String item, Human human){
        Item givenItem = null;
        for (Item itemInBag: this.bag){
            if (item.equals(itemInBag.getName())){
                human.getBag().add(itemInBag);
                givenItem = itemInBag;
            }
        }
        if (givenItem != null){
            this.remFromBag(givenItem);
        }
    }
    
    public void drop(String item){
        Item givenItem = null;
        for (Item itemInBag: this.bag){
            if (item.equals(itemInBag.getName())){
                this.room.addItem(itemInBag);
                givenItem = itemInBag;
            }
        }
        if (givenItem != null){
            this.remFromBag(givenItem);
        }
    }

    public void beaten(){
        this.description = "*This person is unconcious now*";
        this.isUnconscious = true;
    }

    public void talk(Hero human){}

 }