package CLASS.Character;

import CLASS.Game.Dialogue;
import CLASS.Room.*;

public class S_Nerd extends Student{
    private Dialogue myDialogue = new Dialogue();
    private Boolean isQuestStarted = false;
    private Boolean isQuestCompleted = false;
    private Boolean isAfterQuest = false;

        
        public S_Nerd(Room room){
            super("Yugi", "He has a big haircut and a massive necklace.", 10, room);
            myDialogue.setFirstMeet("Hello new one. I have a quest for you, my sworn ennemy the history teacher has stolen something very valuable to me. Get it and i'll reward you.");
            myDialogue.setQuestStart("We can't talk until you've deafeated the beast.");
            myDialogue.setQuestComplete("You've rescued my relic from the grasp of my rival. Thank you.");
            myDialogue.setAfterQuest("I'm so happy to see you again UR DarkMagician form EX.");
            myDialogue.setBeardTalk("Grandpa is that you?!");
        }

        @Override
        public void talk(Hero hero){
            if (hero.searchInBag("RareCard")){
                this.isQuestCompleted = true;
            }
            if (hero.getHasFakeBeard()){
                System.out.println(myDialogue.getBeardTalk());
            }
            else if (isAfterQuest){
                System.out.println(myDialogue.getAfterQuest());
            }
            else if (isQuestCompleted){
                hero.give("RareCard", this);
                System.out.println(myDialogue.getQuestComplete());
                super.give("BoringBook", hero);
                System.out.println("*"+super.getName()+ " gives you a BoringBook*");
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
