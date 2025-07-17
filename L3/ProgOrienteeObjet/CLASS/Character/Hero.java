package CLASS.Character;

import CLASS.Actions.*;
import CLASS.Room.*;
import CLASS.Item.*;
import CLASS.Door.*;
import CLASS.Game.*;

import java.util.*;

public class Hero extends Student implements Attack, Go, Help, Inventory, Look, Quit, Take, Talk, Use {
    private final int defaultPower;
    private int power;
    private Weapon weaponEquip;
    private Boolean weaponChange;
    private ArrayList<Human> peopleBeaten;
    private int nbrOfFights;
    private Boolean hasFakeBeard;

    public Hero(String name, String description, int HPmax, Room room){
        super(name, description, HPmax, room);
        this.defaultPower = 15;
        this.power = defaultPower;
        this.weaponChange = false;
        this.weaponEquip = null;
        this.peopleBeaten = new ArrayList<Human>();
        this.nbrOfFights = 0;
        this.hasFakeBeard = false;
    }

    public Weapon getWeaponEquip(){ return weaponEquip; }
    public Boolean getHasFakeBeard(){ return this.hasFakeBeard; }
    public void setHasFakeBeard(){ this.hasFakeBeard = !this.hasFakeBeard; }
    public ArrayList<Human> getPeopleBeaten(){ return this.peopleBeaten; }


    public Boolean isPersonBeaten(String person){
        for (Human personBeaten: this.peopleBeaten){
            if (person.equals(personBeaten.getName())) { return true; }
        }
        return false;
    }

    public void unequipWeapon(){
        if (this.weaponEquip != null){
            this.getBag().add(this.weaponEquip);
            this.weaponEquip = null;
        }
        this.power = defaultPower;
    }

    public void equipNewWeapon(Weapon newWeapon){
        unequipWeapon();
        this.weaponEquip = newWeapon;
        this.power = newWeapon.getPower();
        this.weaponChange = true;
    }

    @Override
    public void attack(String defender){
        ArrayList<Human> people = super.getRoom().getPeople();
        for (Human person: people){
            if (defender.equals(person.getName())){
                this.nbrOfFights += 1;
                if (this.nbrOfFights >= 3){
                    System.out.println("You got into too many fights and got expelled. :(");
                    this.quit();
                }
                else if (person.getHP() <= this.power){
                    this.peopleBeaten.add(person);
                    person.beaten();
                }
                else {
                    System.out.println("Arrg you won't have me like this, you're too weak!!");
                }
                System.out.println("Be careful, this was fight number " + this.nbrOfFights + ", at your 3rd you'll be fired!");
            }
        }
        
    }

    @Override
    public void go(String room, Scanner scan){
        Map<String, Door> Exits = super.getRoom().getExits();
        if (Exits.containsKey(room)){
            Door door = Exits.get(room);
            Room[] rooms = door.getConnectedRoom();
            Room curRoom;
            Room destination;
            if (rooms[0].getName().equals(room))    { destination = rooms[0]; curRoom = rooms[1]; }
            else                                    { destination = rooms[1]; curRoom = rooms[0];  }
            if (door instanceof DoorOneWay) {
                if (((DoorOneWay)door).isOpenable(curRoom)){
                    door.open();
                    super.setRoom(destination);
                    door.close();
                }
                else { System.out.println("Can't go back home now!"); }
            }
            else if (door instanceof DoorWithCode) {
                System.out.println("This door has a code, what is it?\n");
                String comand = MyScanner.scanCode(scan);
                ((DoorWithCode)door).unlock(comand);
                if (!((DoorWithLock)door).getIsLocked()){
                    door.open();
                    super.setRoom(destination);
                    door.close();
                }
            }
            else if (door instanceof DoorWithKey) {
                if (!((DoorWithLock)door).getIsLocked()){
                    door.open();
                    super.setRoom(destination);
                }
                else System.out.println("This Door needs a key, enter USE [keyName] to unlock the door");
            }
            else if (door instanceof DoorAdult) {
                if (this.hasFakeBeard){
                    door.open();
                    super.setRoom(destination);
                    door.close();
                }
                else { System.out.println("It says only adults are allowed in here"); }
            }
            else {
                door.open();
                super.setRoom(destination);
                door.close();
            }
        }
        else { System.out.println("Wrong room"); }
    }

    @Override
    public void help(){
        System.out.println("Here's a list of all the commands you can use:");
        System.out.println("ATTACK *PERSON*, Allows you to attack someone in the same room as you. Be careful, you are not strong enough to defeat everyone by hand and getting into too many fights will get you expelled.");
        System.out.println("GO *PLACE*, Allows you to go to a neighbouring place if the door leading to it is open.");
        System.out.println("HELP, Allows you to view all the commands and their effects.");
        System.out.println("LOOK, Allows you to get a description of the room you're in.");
        System.out.println("LOOK *thing*, Allows you to get a description of the thing you're looking at.");
        System.out.println("QUIT, Allows you to quit the game.");
        System.out.println("TAKE *item*, Allows you to take an item in the same room as you.");
        System.out.println("USE *item*, Allows you to use an item in your inventory.");
        System.out.println("USE *item* *item*, Allows you to combine 2 items together if possible.");
        System.out.println("INVENTORY, Allows you to look at your inventory.");
        System.out.println("TALK *Human*, Allows to talk with someone witch is sin the same Room as you.");
    }

    @Override
    public void look(){
        System.out.println(super.getRoom().getDescription());
    }

    @Override
    public void look(String thing){
        ArrayList<Human> people = super.getRoom().getPeople();
        ArrayList<Item> items = super.getRoom().getItems();
        ArrayList<Item> itemsInPocket = super.getBag();
        for (Human person: people){
            if (thing.equals(person.getName())){ 
                System.out.println(person.getDescription()); 
            }
        }
        for (Item item: items){
            if (thing.equals(item.getName())){ 
                System.out.println(item.getDescription()); 
            }
        }
        for (Item item: itemsInPocket){
            if (thing.equals(item.getName())){ 
                System.out.println(item.getDescription()); 
            }
        }
    }

    @Override
    public void quit(){
        System.exit(0);
    }

    @Override
    public void take(String i){
        Iterator<Item> iterator = this.room.getItems().iterator();

        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (i.equals(item.getName())) {
                super.addInBag(item);
                iterator.remove(); // Utilisation de l'itérateur pour supprimer en toute sécurité
                System.out.println("You took " + item.getName());
                return; // Ajout d'un return pour sortir de la méthode après avoir pris l'objet
            }
        }
        System.out.println("There is no item named " + i + " in the room.");
    }

    @Override
    public void use(String item){
        Weapon weaponEquip = this.weaponEquip;
        if (weaponEquip != null){
            if (item.equals(weaponEquip.getName())) { weaponEquip.use(this); }
        }
        else{
            for (Item itemInBag: super.getBag()){
                if (item.equals(itemInBag.getName())) { itemInBag.use(this); }
            }
        }
        if (this.weaponChange){
            this.weaponChange = false;
            this.getBag().remove(this.weaponEquip);
        }
    }

    @Override
    public void use(String item1, String item2){
        boolean isItem1 = false;
        boolean isItem2 = false;
        Item i1 = null;
        Item i2 = null;
        for (Item itemInBag: super.getBag()){
            if (item1.equals(itemInBag.getName())) {
                isItem1 = true;
                i1 = itemInBag;
                }
            if (item2.equals(itemInBag.getName())) {
                isItem2 = true;
                i2 = itemInBag;
                }
        }
        if (isItem1 && isItem2) { i1.use(this, i1, i2); }
    }

    @Override
    public void use(String item, Scanner scan){
        for (Item itemInBag: super.getBag()){
            if (item.equals(itemInBag.getName())) { itemInBag.use(scan); }
        }
    }

    @Override
    public void inventory(){
        super.printInBag();
    }

    @Override
    public void talk(String human){
        Human thehumanInRoom = null;
        for (Human humanInRoom: super.room.getPeople()){
            if (human.equals(humanInRoom.getName())) {
                thehumanInRoom = humanInRoom;     
            }
        }
        if (thehumanInRoom != null){
            if (!thehumanInRoom.getIsUnconscious()) { thehumanInRoom.talk(this); }
            else                                    { System.out.println("Can't talk to someone unconcsious"); }
            
        }
        else { System.out.println("Wrong person"); }
    }
    
    
}
