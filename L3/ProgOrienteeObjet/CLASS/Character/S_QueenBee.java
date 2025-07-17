package CLASS.Character;

import CLASS.Game.Dialogue;
import CLASS.Room.*;

public class S_QueenBee extends Student {
    private Dialogue myDialogue = new Dialogue();
    private Boolean isQuestStarted = false;
    private Boolean isQuestCompleted = false;
    private Boolean isAfterQuest = false;

        
        public S_QueenBee(Room room){
            super("Regina", "She looks very confident, maybe too much, and she loooooves to gossip", 10, room);
            myDialogue.setFirstMeet("You're the new kid? Cooool, not at all underwhelming...");
            myDialogue.setQuestStart("Don't talk to me unless you have some hot goss'.");
            myDialogue.setQuestComplete("A LoveLetter? Cuuuuuute. Yeah I'll help you, give it to me");
            myDialogue.setAfterQuest("You seem good at fishing for info, maybe I'll keep you around.");
            myDialogue.setBeardTalk("Ew, that beard looks nasty.");
        }

        @Override
        public void talk(Hero hero){
            if (hero.searchInBag("LoveLetter")){
                this.isQuestCompleted = true;
            }
            if (hero.getHasFakeBeard()){
                System.out.println(myDialogue.getBeardTalk());
            }
            else if (isAfterQuest){
                System.out.println(myDialogue.getAfterQuest());
            }
            else if (isQuestCompleted){
                hero.give("LoveLetter", this);
                System.out.println(myDialogue.getQuestComplete());
                super.setDescription("She reads the LoveLetter while giggling, oops");
                this.isAfterQuest = true;
            }
            else if (isQuestStarted){
                System.out.println(myDialogue.getQuestStart());
            }
            else{
                System.out.println(myDialogue.getFirstMeet());
                this.isQuestStarted = true;
            }
        }
}
