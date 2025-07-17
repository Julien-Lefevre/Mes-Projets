package CLASS.Test.ItemTest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import CLASS.Item.*;

import CLASS.Room.*;
import CLASS.Character.*;

public class WeaponTest {

    private Weapon w1;
    private Weapon w2;
    private Room r;
    private Hero h;

    @Before
    public void setup(){
        w1 = new Weapon("weapon1", "description weapon1", 999);
        w2 = new Weapon("weapon2", "description weapon2", 999);
        r = new Room("room", "description room");
        h = new Hero("hero", "dexcription hero", 1, r);
    }

    @Test
    public void testgetPower(){
        assertEquals(w1.getPower(), 999);
    }

    @Test
    public void testUse(){
        h.equipNewWeapon(w1);
        assertSame(w1, h.getWeaponEquip());
        h.equipNewWeapon(w2);
        assertSame(w2, h.getWeaponEquip());
    }
}