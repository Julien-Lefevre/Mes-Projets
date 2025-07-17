package CLASS.Character;

import CLASS.Game.Dialogue;
import CLASS.Room.*;

public class S_ExchangeStudent extends Student{

    private Dialogue myDialogue = new Dialogue();
    private Boolean isQuestStarted = false;
    private Boolean isQuestCompleted = false;
    private Boolean isAfterQuest = false;
    
    public S_ExchangeStudent(Room room){
        super("Wolfgang", "He comes from Germany, no one understand what he says.", 10, room);
        myDialogue.setFirstMeet("Betrüger, es ist verboten, einen anderen als den implementierten Übersetzer zu verwenden.");
        myDialogue.setQuestStart("*speaks in german*");
        myDialogue.setQuestComplete("I found money floor. Is yours?");
        myDialogue.setAfterQuest("It is nice talking to someone finally.");
        myDialogue.setBeardTalk("Ich bin eine Blume.");
    }

    @Override
    public void talk(Hero hero){
        if (hero.searchInBag("GermanDictionnary")){
            this.isQuestCompleted = true;
        }
        if (hero.getHasFakeBeard()){
            System.out.println(myDialogue.getBeardTalk());
        }
        else if (isAfterQuest){
            System.out.println(myDialogue.getAfterQuest());
        }
        else if (isQuestCompleted){
            System.out.println("I can translate the words he's saying!");
            System.out.println(myDialogue.getQuestComplete());
            System.out.println( "*" + super.getName() + " gives you 30$*");
            super.give("30$", hero);
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