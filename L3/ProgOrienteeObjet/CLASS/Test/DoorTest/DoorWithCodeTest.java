package CLASS.Test.DoorTest;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import CLASS.Room.*;
import CLASS.Door.DoorWithCode;




public class DoorWithCodeTest{

    private Room r1;
    private Room r2;
    private String c1;
    private String c2;
    private DoorWithCode d1;

    @Before
    public void setup(){
        r1 = new Room("room1", "azerty");
        r2 = new Room("room2", "azerty");
        c1 = "1234";
        c2 = "3456";

        d1 = new DoorWithCode(r1, r2, c1);
    }

    @Test
    public void Test_DoorWithCode(){
        assertEquals(d1.getCode(), c1);
    }

    @Test
    public void TestGetKey(){
        assertEquals(d1.getCode(), c1);
    }


    @Test
    public void TestLockAndUnlock(){
        assertTrue(d1.getIsLocked());

        d1.unlock(c1);
        assertFalse(d1.getIsLocked());
        
        d1.lock();
        assertTrue(d1.getIsLocked());

        d1.unlock(c2);
        assertTrue(d1.getIsLocked());
    }
}