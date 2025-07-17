package CLASS.Test.DoorTest;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import CLASS.Room.*;
import CLASS.Door.Door;



public class DoorTest{

    private Room r1;
    private Room r2;
    private Door d1;

    @Before
    public void setup(){
        r1 = new Room("room1", "azerty");
        r2 = new Room("room2", "azerty");

        d1 = new Door(r1, r2);
    }

    @Test
    public void TestDoor(){
        assertEquals(d1.getConnectedRoom()[0] , r1);
        assertEquals(d1.getConnectedRoom()[1] , r2);
    }

    @Test
        public void TestsetRoomMap(){
        assertEquals(r1.getExits().size(), 0);  
        assertEquals(r2.getExits().size(), 0);

        d1.setRoomMap();
        
        assertEquals(r1.getExits().size(), 1);  
        assertEquals(r2.getExits().size(), 1);                      // pas sur que ce soit bon
    }

    @Test
    public void TestOpenClose(){     
        assertFalse(d1.getIsOpen());                                //method wich change the state of a door to open
        
        d1.open();
        assertTrue(d1.getIsOpen());
        
        d1.close();
        assertFalse(d1.getIsOpen());
    }
}