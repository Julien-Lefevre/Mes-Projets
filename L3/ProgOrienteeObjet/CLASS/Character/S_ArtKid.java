package CLASS.Character;

import CLASS.Game.Dialogue;
import CLASS.Room.*;

public class S_ArtKid extends Student{
    private Dialogue myDialogue = new Dialogue();
    private Boolean isQuestStarted = false;
    private Boolean isQuestCompleted = false;
    private Boolean isAfterQuest = false;

        
        public S_ArtKid(Room room){
            super("Venus", "She's wearing overalls and is full of paint stain", 10, room);
            myDialogue.setFirstMeet("Hey, wanna see what I'm working on? It's a little project of mine but I'm missing ChalkPowder. Do you think you could get me some? I'll help you crack the Principal's code if you do.");
            myDialogue.setQuestStart("Have you found the ChalkPowder yet? It's in the StorageRoom.");
            myDialogue.setQuestComplete("Thank you, I'll finally be able to finish my piece. As promised, here's a tip, use some on that powder on the keypad to see the numbers. She goes left to right and down to up.");
            myDialogue.setAfterQuest("Remember, left to right and down to up with the ChalkPowder on her keypad.");
            myDialogue.setBeardTalk("It's a powerful way to express yourself wearing that beard.");
        }

        @Override
        public void talk(Hero hero){
            if (hero.searchInBag("ChalkPowder")){
                this.isQuestCompleted = true;
            }
            if (hero.getHasFakeBeard()){
                System.out.println(myDialogue.getBeardTalk());
            }
            else if (isAfterQuest){
                System.out.println(myDialogue.getAfterQuest());
            }
            else if (isQuestCompleted){
                System.out.println(myDialogue.getQuestComplete());
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
