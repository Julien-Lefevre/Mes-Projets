package CLASS.Door;

import CLASS.Room.*;


public class DoorAdult extends Door{

                                            //Special Doors that can be opened by Adults or hero with fake beard
    public DoorAdult(Room a, Room b){       //default constructors for Instantiating DoorAdult
        super(a, b);
    }

    public void open(){                     //method wich change the state of a door to open
        super.open();
    }

    public void close() {                   //method wich change the state of a door to close
        super.close();
    }
}