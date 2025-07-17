package CLASS.Door;
import CLASS.Room.*;

public abstract class DoorWithLock extends Door {

    private Boolean isLocked;
    

    public DoorWithLock(Room room1, Room room2){        //default constructors for Instantiating DoorWithLock
        super(room1, room2);
        this.isLocked = true;
    }

    @Override
    public void open(){                                 //method that open the Door if it is not locked
        if (!isLocked){
            super.open();
        }
    }

    @Override
    public void close(){                                //method that closes and lock the Door
        super.close();
        this.lock();
    }

    public void lock(){                                 //method that lock the door
        this.isLocked = true;
    }

    public void unlock(){                               //method that unlock the door
        this.isLocked = false;
    }

    public Boolean getIsLocked(){                       //method that return the state of door if it's lock or not
        return this.isLocked;
    }
}