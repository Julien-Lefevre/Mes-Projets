package CLASS.Character;

import CLASS.Game.*;
import CLASS.Room.*;

public class A_ChemistryTeacher extends Adult {
    private TheGame game = TheGame.getInstance();
    private Dialogue myDialogue = new Dialogue();
    private Boolean isQuestStarted = false;
    private Boolean isQuestCompleted = false;
    private Boolean isAfterQuest = false;

    public A_ChemistryTeacher(Room room){
        super("Heisenberg", "This is Heisenberg the chemistry teacher, he looks very stressed.", 50, room);
        myDialogue.setFirstMeet("Sorry the class doesn't start until 4pm.");
        myDialogue.setQuestStart("No, you're not allowed to use the lab on my watch.");
        myDialogue.setQuestComplete("Smoking is bad for you, this is confiscated.");
        myDialogue.setAfterQuest("These are my cigarettes, yours are locked away!");
        myDialogue.setBeardTalk("Ho, hello Sir, i'm totally fine");
    }

  @Override
    public void talk(Hero hero){
        if (hero.searchInBag("CigarettePack")){
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
            System.out.println("*"+ this.getName() + " takes your CigarettePack then leaves the room, dropping something behind*");
            hero.give("CigarettePack", this);
            super.drop("FireCracker");
            super.getRoom().getPeople().remove(this);
            super.setRoom(game.FrontYard);
            super.setDescription("This is Heisenberg the chemistry teacher, he's smoking and looking stressed.");
            super.getRoom().addHuman(this);
            this.isAfterQuest = true;
        }
        else if (isQuestStarted){
            System.out.println(myDialogue.getQuestStart());
        }
        else{
            System.out.println(myDialogue.getFirstMeet());
            isQuestStarted = true;
        }
    }
}