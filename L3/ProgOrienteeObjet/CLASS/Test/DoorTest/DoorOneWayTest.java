package CLASS.Test.DoorTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import CLASS.Door.DoorOneWay;
import CLASS.Room.Room;

public class DoorOneWayTest {

    private Room r1;
    private Room r2;

    private DoorOneWay d1;

    @Before
    public void setup(){
        r1 = new Room("room1", "azerty");
        r2 = new Room("room2", "azerty");

        d1 = new DoorOneWay(r1, r2);
    }

    @Test
    public void isOpenableTest(){
        assertEquals(d1.isOpenable(r1), true);
        assertEquals(d1.isOpenable(r2), false);
    }
}
