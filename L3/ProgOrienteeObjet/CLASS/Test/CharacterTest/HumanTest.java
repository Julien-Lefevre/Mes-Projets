package CLASS.Test.CharacterTest;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import CLASS.Character.A_MathsTeacher;
import CLASS.Character.Human;
import CLASS.Item.Item;
import CLASS.Room.Room;

public class HumanTest {

    private String description = "jaeifjoaizjef";
    private Room room;
    private Human human1;
    private Human human2;
    private Item cigarettePack;

    @Before
    public void setup(){
        room = new Room("secret", description);
        cigarettePack = new Item ("CigarettePack", "This is a cigarette pack, may be useful... to someone else");
        human1 = new A_MathsTeacher(room);
        human2 = new A_MathsTeacher(room);
        human1.addInBag(cigarettePack);
        room.addHuman(human1);
    } 

    @Test
    public void Human_Test(){
        assertEquals(human1.getDescription(), "He seem to be on another level of conciousness, I wonder what's in his head.");
        assertEquals(human1.getRoom(), room);
    }

    @Test
    public void searchInBagTest(){
        assertTrue(human1.searchInBag("CigarettePack"));
        human1.remFromBag(cigarettePack);
        assertFalse(human1.searchInBag("CigarettePack"));
    }

    @Test
    public void sethumanInRoomTest(){
        assertTrue(human1.getRoom().getPeople().contains(human1));
    }

    @Test
    public void giveTest(){
        assertTrue(human1.searchInBag("CigarettePack"));
        assertFalse(human2.searchInBag("CigarettePack"));
        human1.give("CigarettePack", human2);
        assertFalse(human1.searchInBag("CigarettePack"));
        assertTrue(human2.searchInBag("CigarettePack"));
    }

    @Test
    public void dropTest(){
        assertTrue(human1.searchInBag("CigarettePack"));
        assertFalse(room.getItems().contains(cigarettePack));
        human1.drop("CigarettePack");
        assertFalse(human1.searchInBag("CigarettePack"));
        assertTrue(room.getItems().contains(cigarettePack));
    }

    @Test
    public void beatenTest(){
        assertEquals(human1.getDescription(), "He seem to be on another level of conciousness, I wonder what's in his head.");
        assertFalse(human2.getIsUnconscious());
        human2.beaten();
        assertEquals(human1.getDescription(), "He seem to be on another level of conciousness, I wonder what's in his head.");
        assertTrue(human2.getIsUnconscious());
    }
}
