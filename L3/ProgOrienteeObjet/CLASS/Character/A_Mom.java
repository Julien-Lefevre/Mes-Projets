package CLASS.Character;

import CLASS.Game.Dialogue;
import CLASS.Room.*;

public class A_Mom extends Student{
    private Dialogue myDialogue = new Dialogue();
    private Boolean isQuestStarted = false;
    private Boolean isQuestCompleted = false;
    private Boolean isAfterQuest = false;

        
        public A_Mom(Room room){
            super("Mom", "This is my Mom, she's still angry about me having to change school again", 50, room);
            myDialogue.setFirstMeet("Finally awake! Get you Diary before going to school, how are you gonna study without it huh?");
            myDialogue.setQuestStart("You found your Diary yet? It's probably in your [BedRoom]");
            myDialogue.setQuestComplete("Great, now go before being late. You're meeting your principal in the Frontyard.");
            myDialogue.setAfterQuest("I said go to the Frontyard, now!");
            myDialogue.setBeardTalk("ahhhhh, a thief, call an ambulance but not for me!");
        }

        @Override
        public void talk(Hero hero){
            if (hero.searchInBag("Diary")){
                this.isQuestCompleted = true;
            }
            if (hero.getHasFakeBeard()){
                System.out.println(myDialogue.getBeardTalk());
            }
            else if (isAfterQuest){
                System.out.println(myDialogue.getAfterQuest());
            }
            else if (isQuestCompleted){
                this.isAfterQuest = true;
                System.out.println(myDialogue.getQuestComplete());
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