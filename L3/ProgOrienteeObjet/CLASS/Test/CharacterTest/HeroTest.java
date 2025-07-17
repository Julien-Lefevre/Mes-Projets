package CLASS.Test.CharacterTest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import CLASS.Character.*;

import CLASS.Room.*;
import CLASS.Item.*;

public class HeroTest {

    private Room room;
    private Hero hero;

    private A_MathsTeacher person;
    private Weapon weapon1;
    private Weapon weapon2;

    private Item item;

    @Before
    public void setup(){
        room = new Room("room", "description room");
        hero = new Hero("hero", "description hero", 1, room);

        weapon1 = new Weapon("weapon1", "description weapon1", 100);
        weapon2 = new Weapon("weapon2", "description weapon2", 200);
        person = new A_MathsTeacher(room);
        room.addHuman(person);

        item = new Item("item", "description item");
    }

    @Test
    public void testGetSet(){
        assertNull(hero.getWeaponEquip());
        assertFalse(hero.getHasFakeBeard());
        hero.setHasFakeBeard();
        assertTrue(hero.getHasFakeBeard());
        assertEquals(hero.getPeopleBeaten().size(), 0);
    }

    @Test
    public void testWeaponAttack(){
        hero.addInBag(weapon1);
        hero.addInBag(weapon2);

        hero.equipNewWeapon(weapon1);
        assertEquals(hero.getWeaponEquip(), weapon1);

        hero.equipNewWeapon(weapon2);
        assertEquals(hero.getWeaponEquip(), weapon2);
        assertTrue(hero.searchInBag("weapon1"));

        assertEquals(hero.getPeopleBeaten().size(), 0);
        hero.attack("Pythagore");
        assertEquals(hero.getPeopleBeaten().size(), 1);
        assertTrue(hero.isPersonBeaten("Pythagore"));
    }

    @Test
    public void testTake(){
        room.addItem(item);
        assertEquals(room.getItems().size(), 1);
        assertEquals(hero.getBag().size(), 0);

        hero.take("notItem");
        assertEquals(room.getItems().size(), 1);
        assertEquals(hero.getBag().size(), 0);

        hero.take("item");
        assertEquals(room.getItems().size(), 0);
        assertEquals(hero.getBag().size(), 1);
    }

    @Test
    public void testEquipUnequipUseWeapon(){
        hero.addInBag(weapon1);
        assertNull(hero.getWeaponEquip());
        assertEquals(hero.getBag().size(), 1);

        hero.use("weapon1");
        assertEquals(hero.getWeaponEquip(), weapon1);
        assertEquals(hero.getBag().size(), 0);

        hero.use("weapon1");
        assertNull(hero.getWeaponEquip());
        assertEquals(hero.getBag().size(), 1);
    }
}