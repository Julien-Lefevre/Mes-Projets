package CLASS.Character;

import CLASS.Game.Dialogue;
import CLASS.Room.*;

public class S_Rebel extends Student{

    private Dialogue myDialogue = new Dialogue();
    private Boolean isQuestStarted = false;
    private Boolean isQuestCompleted = false;
    private Boolean isAfterQuest = false;
    public S_Rebel(Room room){
        super("Lester", "He thinks he looks scary with his malabar's tatoos and his leather jacket.", 10, room);
        myDialogue.setFirstMeet("Hey, you're the new kid right? I'm Lester, but everybody calls me Red. What do you say we become friends? And you know what friends do? Help each other, so help me and I'll help you.\nSomeone stole 30$ from me, find it and i'll give you something in return");
        myDialogue.setQuestStart("Have you found my money yet?? Go get it!");
        myDialogue.setQuestComplete("Thank you, you're a real one, here take this.");
        myDialogue.setAfterQuest("You got your stuff, now SCRAM!");
        myDialogue.setBeardTalk("Are you a cop or something? You have to tell me if you're a cop!");
    }

    @Override
    public void talk(Hero hero){
        if (hero.searchInBag("30$")){
            this.isQuestCompleted = true;
        }
        if (hero.getHasFakeBeard()){
            System.out.println(myDialogue.getBeardTalk());
        }
        else if (isAfterQuest){
            System.out.println(myDialogue.getAfterQuest());
        }
        else if (isQuestCompleted){
            hero.give("30$", this);
            System.out.println(myDialogue.getQuestComplete());
            super.give("CigarettePack", hero);
            System.out.println("*"+super.getName()+ " gives you a CigarettePack*");
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