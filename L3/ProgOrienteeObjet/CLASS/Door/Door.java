package CLASS.Door;

import CLASS.Room.*;

public class Door {
    private final Room[] connect;                               //Where the 2 rooms the door connects will be stored
    private Boolean isOpen;                                     //print the state of a door(open or close)

    public Door(Room room1, Room room2){                        //default constructors for Instantiating Door 
        this.connect = new Room[2];
        this.connect[0] = room1;
        this.connect[1] = room2;
        this.isOpen = false;                                    //Door is by default close
    }

    public void setRoomMap(){                                   //add to Exit in Room.java the new Door with his room
        connect[0].addExit(connect[1], this);
        connect[1].addExit(connect[0], this);
    }

    public void open(){                                         //method wich change the state of a door to open
        this.isOpen = true;
    }

    public void close() {                                       //method wich change the state of a door to close
        this.isOpen = false;
    }

    public Room[] getConnectedRoom(){return this.connect;}      //Getter who send back connect

    public boolean getIsOpen() {                                //return the state of the door
        return this.isOpen;
    }

}