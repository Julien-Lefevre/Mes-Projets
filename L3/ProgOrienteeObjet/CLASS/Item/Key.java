package CLASS.Item;

import java.util.*;

import CLASS.Character.*;
import CLASS.Door.*;

public class Key extends Item {

    public Key(String name, String description){                                                                 //default constructors for Instantiating Key
        super(name, description);
    }


    public void use(Hero hero){                                                                                  //method use that allow the Hero to use a key and open a door if it's the right key
        ArrayList <Door> connectedDoors = new ArrayList<Door>(hero.getRoom().getExits().values());
        for (Door door: connectedDoors){
            if (door instanceof DoorWithKey){
                if (((DoorWithKey)door).getKey().equals(this)) {
                    ((DoorWithKey)door).unlock();
                    if (!door.getConnectedRoom()[0].equals(hero.getRoom())) { System.out.println("you opened the door to " + door.getConnectedRoom()[0].getName()); }
                    else                                                    { System.out.println("you opened the door to " + door.getConnectedRoom()[1].getName()); }
                }
            }
        }
    }
}