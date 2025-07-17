package CLASS.Test.ItemTest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import CLASS.Item.*;

import CLASS.Door.*;
import CLASS.Room.*;
import CLASS.Character.*;

public class KeyTest {

    private Key key;
    private Room room1;
    private Room room2;
    private DoorWithKey door;
    private Hero hero;

    @Before
    public void setup(){
        key = new Key("key", "description key");
        room1 = new Room("room1", "description room1");
        room2 = new Room("room2", "description room2");
        door = new DoorWithKey(room1, room2, key);
        door.setRoomMap();
        hero = new Hero("hero", "dexcription hero", 1, room1);
    }

    @Test
    public void testUse(){
        hero.addInBag(key);

        assertTrue(door.getIsLocked());
        key.use(hero);
        assertFalse(door.getIsLocked());
    }
}