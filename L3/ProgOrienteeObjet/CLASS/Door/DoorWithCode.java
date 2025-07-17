package CLASS.Door;
import CLASS.Room.*;

public class DoorWithCode extends DoorWithLock {
    private final String CODE;

    public DoorWithCode(Room room1, Room room2, String Code){       //default constructors for Instantiating DoorWithCode
        super(room1, room2);
        this.CODE = Code;
    }

    public String getCode(){return this.CODE;}                      //Getter on the private attribute CODE

    
    public void unlock(String tryCode){                             //method that try the Code on the door and open it if this is the right code
        if (this.CODE.equals(tryCode)){
            super.unlock();
            System.out.println("RIGHT CODE !");
        }
        else System.out.println("WRONG CODE !");
    }


}