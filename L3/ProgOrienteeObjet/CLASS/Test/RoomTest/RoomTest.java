package CLASS.Test.RoomTest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

import CLASS.Room.*;

import CLASS.Item.*;
import CLASS.Door.*;
import CLASS.Character.*;

public class RoomTest {

    private Room room1;
    private Room room2;
    private Human human1;
    private Human human2;
    private Item item1;
    private Item item2;
    private Door door1;
    private Door door2;


    @Before
    public void setup(){
        room1 = new Room("room1", "description room1");
        room2 = new Room("room2", "description room2");
        human1 = new S_ArtKid(room1);
        human2 = new A_ArtTeacher(room1);
        item1 = new Item("item1", "description item1");
        item2 = new Item("item2", "description item2");
        door1 = new Door(room1, room2);
        door2 = new Door(room1, room2);
    }

    @Test
    public void testGetName(){
        String name1 = room1.getName();
        assertEquals(name1, "room1");
    }

    @Test
    public void testItems(){
        room1.addItem(item1);
        ArrayList<Item> items1 = room1.getItems();
        ArrayList<Item> items2 = new ArrayList<Item>();
        items2.add(item1);
        assertEquals(items1, items2);
        assertEquals(items1.size(), 1);

        room1.addItem(item2);
        items1 = room1.getItems();
        items2.add(item2);
        assertEquals(items1, items2);
        assertEquals(items1.size(), 2);
    }

    @Test
    public void testHumans(){
        room1.addHuman(human1);
        ArrayList<Human> peopleInRoom = room1.getPeople();
        ArrayList<Human> peopleTest = new ArrayList<Human>();
        peopleTest.add(human1);
        assertEquals(peopleInRoom, peopleTest);
        assertEquals(peopleInRoom.size(), 1);

        room1.addHuman(human2);
        peopleInRoom = room1.getPeople();
        peopleTest.add(human2);
        assertEquals(peopleInRoom, peopleTest);
        assertEquals(peopleInRoom.size(), 2);

        room1.rmPeopleFromRoom(human2);
        peopleInRoom = room1.getPeople();
        peopleTest.remove(human2);
        assertEquals(peopleInRoom, peopleTest);
        assertEquals(peopleInRoom.size(), 1);
    }

    @Test
    public void testDescription(){
        assertEquals(room2.getDescription(), "description room2\n");
        room2.addItem(item1);
        room2.addItem(item2);
        room2.addHuman(human1);
        room2.addHuman(human2);
        assertEquals(room2.getDescription(), "description room2\n I can see item(s): item1, item2.\n I'm  not alone here, there is: Venus, MonaLisa.\n");
    }

    @Test
    public void testMap(){
        room1.addExit(room2, door1);
        room1.addExit(room2, door2);
        Map<String, Door> Exits = new HashMap<>();
        Exits.put("room2", door1);
        Exits.put("room2", door2);
        assertEquals(room1.getExits(), Exits);
    }

    @Test
    public void testRmEmpty() {
        room1.rmPeopleFromRoom(human2);
    }
}