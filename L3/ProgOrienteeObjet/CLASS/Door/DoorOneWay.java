package CLASS.Door;

import CLASS.Room.*;


public class DoorOneWay extends Door{
    private Room GoOut;

                                                //You can only open the door from the first room and this door can only be opened in one direction
    public DoorOneWay(Room a, Room b){          //default constructors for Instantiating DoorOneWay
        super(a, b);
        this.GoOut = a;
    }

    public boolean isOpenable(Room a){          //return boolean that show if you can open the door from where you are
        return (a.equals(GoOut));
    }

    public void open(Room a){                   //method which open the door if isOpenable is valid
        if (isOpenable(a)){
            super.open();
        }
    }
}