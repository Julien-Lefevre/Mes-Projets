package CLASS.Test.ItemTest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import CLASS.Item.*;

import CLASS.Character.*;
import CLASS.Room.*;

public class ItemTest {

    private Item hair;
    private Item glue;
    private Item fakebeard;
    private Item bread;
    private Item ingredients;
    private Item chalkpowder;
    private Item firecracker;
    private Item examsubject;
    private Room firstfloor;
    private Hero h;

    @Before
    public void setup(){
        hair        = new Item("Hair",          "description hair");
        glue        = new Item("Glue",          "description glue");
        fakebeard   = new Item("FakeBeard",     "description fakebeard");
        bread       = new Item("Bread",         "description bread");
        ingredients = new Item("Ingredients",   "description ingredients");
        chalkpowder = new Item("Hair",          "description chalkpowder");
        firecracker = new Item("Firecracker",   "description firecracker");
        examsubject = new Item("ExamSubject",   "description examsubject");

        h                   = new Hero("hero","description hero", 1, firstfloor);
    }

    @Test
    public void testGet(){
        assertEquals(hair.getName(), "Hair");
        assertEquals(hair.getDescription(), "description hair");
    }

    @Test
    public void testCreate(){
        assertFalse(hair.createFakeBeard(hair, bread));
        assertTrue(hair.createFakeBeard(hair, glue));
        assertTrue(hair.createFakeBeard(glue, hair));
        
        assertFalse(bread.createSandwich(bread, hair));
        assertTrue(bread.createSandwich(bread, ingredients));
        assertTrue(bread.createSandwich(ingredients, bread));
    }

    @Test
    public void testUseItem(){
        h.addInBag(fakebeard);
        h.addInBag(chalkpowder);
        h.addInBag(firecracker);
        h.addInBag(examsubject);

        assertFalse(h.getHasFakeBeard());
        fakebeard.use(h);
        assertTrue(h.getHasFakeBeard());

        //can't test chalkpowder

        //can't test FireCracker

        //can't test examsubject
    }

    @Test
    public void testUseItemItem(){
        h.addInBag(hair);
        h.addInBag(glue);
        h.addInBag(bread);
        h.addInBag(ingredients);

        assertEquals(h.getBag().size(), 4);
        hair.use(h, hair, glue);
        bread.use(h, bread, ingredients);
        assertEquals(h.getBag().size(), 2);
        assertEquals(h.getBag().get(0).getName(), "FakeBeard");
        assertEquals(h.getBag().get(1).getName(), "Sandwich");
    }
}