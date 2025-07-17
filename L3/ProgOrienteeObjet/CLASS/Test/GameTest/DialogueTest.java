package CLASS.Test.GameTest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import CLASS.Game.*;


public class DialogueTest {

    private Dialogue myDialogue;

    private String firstMeet;
    private String questStart;
    private String questComplete;
    private String afterQuest;
    private String beardTalk;

    @Before
    public void setup(){
        myDialogue = new Dialogue();

        firstMeet = "FirstMeet";
        questStart = "QuestStart";
        questComplete = "QuestComplete";
        afterQuest = "AfterQuest";
        beardTalk = "BeardTalk";
    }

    @Test
    public void testUse(){
        assertEquals(myDialogue.getFirstMeet(), "");
        assertEquals(myDialogue.getQuestStart(), "");
        assertEquals(myDialogue.getQuestComplete(), "");
        assertEquals(myDialogue.getAfterQuest(), "");
        assertEquals(myDialogue.getBeardTalk(), "");

        myDialogue.setFirstMeet(firstMeet);
        myDialogue.setQuestStart(questStart);
        myDialogue.setQuestComplete(questComplete);
        myDialogue.setAfterQuest(afterQuest);
        myDialogue.setBeardTalk(beardTalk);

        assertEquals(myDialogue.getFirstMeet(), firstMeet);
        assertEquals(myDialogue.getQuestStart(), questStart);
        assertEquals(myDialogue.getQuestComplete(), questComplete);
        assertEquals(myDialogue.getAfterQuest(), afterQuest);
        assertEquals(myDialogue.getBeardTalk(), beardTalk);
    }
}