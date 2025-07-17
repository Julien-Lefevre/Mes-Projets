package CLASS.Character;

import CLASS.Game.Dialogue;
import CLASS.Room.*;

public class A_HistoryTeacher extends Adult {
    private Dialogue myDialogue = new Dialogue();
    private Boolean isQuestStarted = false;
    private Boolean isQuestCompleted = false;
    private Boolean isAfterQuest = false;

        
        public A_HistoryTeacher(Room room){
            super("Salomon", "He looks like a sweet old man, his stomach is rumbling", 50, room);
            myDialogue.setFirstMeet("Oh hey, sorry the class hasn't started yet.");
            myDialogue.setQuestStart("I really shouldn't have skipped lunch.");
            myDialogue.setQuestComplete("Is that for me? Thank you! Here have this, i stole it from some nerd.");
            myDialogue.setAfterQuest("Have I mentionned I love old safes?");
            myDialogue.setBeardTalk("Hello sir. You look a lot like me when I was younger, weird...");
        }

        @Override
        public void talk(Hero hero){
        if (hero.searchInBag("Sandwich")){
            this.isQuestCompleted = true;
        }
            if (hero.getHasFakeBeard()){
                System.out.println(myDialogue.getBeardTalk());
            }
            else if (isAfterQuest){
                System.out.println(myDialogue.getAfterQuest());
            }
            else if (isQuestCompleted){
                hero.give("Sandwich", this);
                System.out.println(myDialogue.getQuestComplete());
                super.give("RareCard", hero);
                System.out.println("*"+super.getName()+ " gives you a RareCard*");
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