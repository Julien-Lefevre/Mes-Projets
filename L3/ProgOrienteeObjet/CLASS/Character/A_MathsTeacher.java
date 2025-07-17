package CLASS.Character;

import CLASS.Game.Dialogue;
import CLASS.Room.*;

public class A_MathsTeacher extends Adult {
    private Dialogue myDialogue = new Dialogue();
    private Boolean isQuestStarted = false;
    private Boolean isQuestCompleted = false;
    private Boolean isAfterQuest = false;

        
        public A_MathsTeacher(Room room){
            super("Pythagore", "He seem to be on another level of conciousness, I wonder what's in his head.", 50, room);
            myDialogue.setFirstMeet("You must be new, hi! I'm your math teacher Pythagore.");
            myDialogue.setQuestStart("Sorry I'm busy at the moment.");
            myDialogue.setQuestComplete("I said I'm busy.");
            myDialogue.setAfterQuest("What do you want from me?!");
            myDialogue.setBeardTalk("Sorry sir I'm busy");
        }

        @Override
        public void talk(Hero hero){
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
                this.isQuestCompleted = true;
            }
            else{
                System.out.println(myDialogue.getFirstMeet());
                this.isQuestStarted = true;
            }
        }

        public Dialogue getMyDialogue() { return this.myDialogue; }  //This method was created for the class: DialogueTest
}