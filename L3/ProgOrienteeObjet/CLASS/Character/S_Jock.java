package CLASS.Character;

import CLASS.Game.Dialogue;
import CLASS.Room.*;

public class S_Jock extends Student{
    private Dialogue myDialogue = new Dialogue();
    private Boolean isQuestStarted = false;
    private Boolean isQuestCompleted = false;
    private Boolean isAfterQuest = false;

        
        public S_Jock(Room room){
            super("Brian", "He is a very muscular guy wearing skinny jean, he looks smart said no one.", 20, room);
            myDialogue.setFirstMeet("Hello there, who are you? Would you mind cleaning the LockerRoom for me? Here take the key.");
            myDialogue.setQuestStart("Done cleaning yet?");
            myDialogue.setQuestComplete("Ewwwwwwww gross, you could make a FakeBeard with those hair and pretend to be a adult lol.");
            myDialogue.setAfterQuest("Are you a fan or something?");
            myDialogue.setBeardTalk("Hello Sir, are you our new coach?");
        }

        @Override
        public void talk(Hero hero){
            if (hero.searchInBag("Hair")){
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
                super.give("Key_Gym_LockerRoom", hero);
                System.out.println("*"+super.getName()+ " gives you a key_Gym_LockerRoom*");
                this.isQuestStarted = true;
            }
        }
}
