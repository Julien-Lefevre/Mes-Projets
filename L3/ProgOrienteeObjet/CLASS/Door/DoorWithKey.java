package CLASS.Door;
import CLASS.Room.*;
import CLASS.Item.*;

public class DoorWithKey extends DoorWithLock {

    private final Key key;

    public DoorWithKey(Room room1, Room room2, Key doorKey){        //default constructors for Instantiating DoorWithKey
        super(room1,room2);
        this.key = doorKey;
    }

    public Key getKey() { return this.key; }                        //Getter that return the private attribute key

    public Boolean isTheKey (String newKey){                        //method which return a boolean for is it the right key
        return (this.key.getName().equals(newKey));
    }

    public void lock (Key newKey){                                  //method that lock the Door if the right key is used
        if(isTheKey(newKey.getName())){
            super.lock();
        }
    }
    
    public void unlock (String newKey){                             //method that unlock the Door if the right String key is used
        if(isTheKey(newKey)){
            super.unlock();
        }
    }
}
