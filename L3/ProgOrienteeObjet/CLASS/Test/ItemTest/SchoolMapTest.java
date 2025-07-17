package CLASS.Test.ItemTest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import CLASS.Item.*;

import CLASS.Character.*;
import CLASS.Room.*;

public class SchoolMapTest {

    private SchoolMap sm;
    private Room r;
    private Hero h;


    @Before
    public void setup(){
        sm = new SchoolMap("schoolMap", "description sm");
        r = new Room("room", "description room");
        h = new Hero("hero", "dexcription hero", 1, r);
    }

    @Test
    public void testGetName(){
        h.addInBag(sm);
        String map = "_____________________________________________________________________________________________________________________________________\n|             |                        |  @@  |                      |               |  @@  |               |       |               |\n| LockerRoom  |                      <-|  @@  |<-<-<-<-<-<-<-  Hall  |               |  @@  |   Principal   .       .    Theatre    |\n|_..._________|                      <-|  @@  |<-<-<-<-<-<-<-        |               |  @@  |    Office     .       .     Class     |\n|             |                        |  @@  |_______________       |               |  @@  |               |       |_______________|\n|             |                        |  @@  |    ToiletM    .      .    Library    |  @@  |_______________|       |               |\n|             .       SchoolYard       |  @@  |_______________|      .               |  @@  |               |       .    History    |\n|     Gym     .                        |  @@  |    ToiletW    .      |               |  @@  |     Maths     .       .     Class     |\n|             |                        |  @@  |_______________|      |               |  @@  |     Class     .       |_______________|\n|             |                        |  @@  |               |      |_______________|  @@  |_______________|       |               |\n|             |                        |  @@  |               |                      |  @@  |                       .      Art      |\n|_____________|__________...___________|  @@  |               |      ->->->->->->->->|  @@  |->->->->->- FirstFloor .     Class     |\n|                                      |  @@  |               .       _______________|  @@  |_______________        |_______________|\n|                                      |  @@  |   Cafeteria   .      |               |  @@  |               |       |               |\n|                                      |  @@  |               |      |               |  @@  |   Chemistry   .       .   Philosophy  |\n|                                      |  @@  |               |      .   Infirmary   |  @@  |     Class     .       .     Class     |\n|                                      |  @@  |               |      .               |  @@  |_______________|       |_______________|\n|             SportField               |  @@  |               |      |               |  @@  |               |       |               |\n|                                      |  @@  |_______________|      |_______________|  @@  |               |       |               |\n|                                      |  @@  |               |      |               |  @@  |    Storage    .       .     Staff     |\n|                                      |  @@  |   Counselor   .      .   Reception   |  @@  |     Room      .       .      Room     |\n|                                      |  @@  |    Office     .      .               |  @@  |               |       |               |\n|______________________________________|  @@  |_______________|======|_______________|  @@  |_______________|_______|_______________|\n|                                         @@                                            @@                                          |\n|                                         @@                 Frontyard                  @@                                          |\n|                                         @@                                            @@                                          |\n|                                         @@                                            @@                                          |\n|             -----------                 @@               ----------------             @@               ---------------            |\n|             ||Outside||                 @@               ||Ground Floor||             @@               ||First Floor||            |\n|             -----------                 @@               ----------------             @@               ---------------            |\n|_________________________________________@@____________________________________________@@__________________________________________|\n";
        assertEquals(map, sm.getContent());
    }
}