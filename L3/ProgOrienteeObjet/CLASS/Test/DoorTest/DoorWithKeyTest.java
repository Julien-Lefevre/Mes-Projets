package CLASS.Test.DoorTest;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import CLASS.Room.*;
import CLASS.Door.DoorWithKey;
import CLASS.Item.Key;




public class DoorWithKeyTest{

    private Room r1;
    private Room r2;
    private Key k1;
    private Key k2;
    private DoorWithKey d1;

    @Before
    public void setup(){
        r1 = new Room("room1", "azerty");
        r2 = new Room("room2", "azerty");
        k1 = new Key("a key", "null");
        k2 = new Key("not this one", "null");

        d1 = new DoorWithKey(r1, r2, k1);
    }

    @Test
    public void Test_DoorWithKey(){
        assertEquals(k1, d1.getKey());
    }

    @Test
    public void TestGetKey(){
        assertEquals(d1.getKey(), k1);
    }

    @Test
    public void TestIsTheKey(){
        assertEquals(d1.isTheKey(k2.getName()), false);
        assertEquals(d1.isTheKey(k1.getName()), true);
    }

    @Test
    public void TestLockAndUnlock(){
        assertTrue(d1.getIsLocked());

        d1.unlock(k2.getName());
        assertTrue(d1.getIsLocked());

        d1.unlock(k1.getName());
        assertFalse(d1.getIsLocked());

        d1.lock(k1);
        assertTrue(d1.getIsLocked());
    }
}