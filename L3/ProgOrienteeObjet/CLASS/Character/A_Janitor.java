package CLASS.Character;

import CLASS.Game.Dialogue;
import CLASS.Room.*;

public class A_Janitor extends Adult {
    private Dialogue myDialogue = new Dialogue();
    private Boolean isQuestStarted = false;
    private Boolean isQuestCompleted = false;
    private Boolean isAfterQuest = false;

        
        public A_Janitor(Room room){
            super("Mathew", "Wow he's tall, he looks pretty lost though", 50, room);
            myDialogue.setFirstMeet("Hey kid, I'm the Janitor, could you help me find my Glasses? I can see them without my... yeah.");
            myDialogue.setQuestStart("Any luck with my Glasses? They should be on the ground floor.");
            myDialogue.setQuestComplete("Thank you, I can finally see again! Here use these to get you a treat.");
            myDialogue.setAfterQuest("Found anything good in the StorageRoom?");
            myDialogue.setBeardTalk("Hello sir, can I help you?");
        }

        @Override
        public void talk(Hero hero){
            if (hero.searchInBag("Glasses")){
                this.isQuestCompleted = true;
            }
            if (hero.getHasFakeBeard()){
                System.out.println(myDialogue.getBeardTalk());
            }
            else if (isAfterQuest){
                System.out.println(myDialogue.getAfterQuest());
            }
            else if (isQuestCompleted){
                hero.give("Glasses", this);
                System.out.println(myDialogue.getQuestComplete());
                super.give("Key_FirstFloor_StorageRoom", hero);
                System.out.println("*"+super.getName()+ " gives you the keys to the StorageRoom*");
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