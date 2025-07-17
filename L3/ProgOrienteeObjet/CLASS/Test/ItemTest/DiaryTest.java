package CLASS.Test.ItemTest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import CLASS.Item.*;

public class DiaryTest {

    private Diary d;

    @Before
    public void setup(){
        d = new Diary("Diary", "description diary");
    }

    @Test
    public void testContent(){
        String txt = d.getContent();
        assertEquals(txt, "");
    }
}