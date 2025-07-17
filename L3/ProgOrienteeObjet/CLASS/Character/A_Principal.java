package CLASS.Character;

import CLASS.Game.Dialogue;
import CLASS.Game.TheGame;
import CLASS.Room.*;

public class A_Principal extends Adult {

    private TheGame game = TheGame.getInstance();
    private Dialogue myDialogue = new Dialogue();
    private Boolean isQuestStarted = false;
    private Boolean isQuestCompleted = false;
    private Boolean isAfterQuest = false;
    public A_Principal(Room room){
        super("MissTrunchBull", "She's the Principal of this wonderful school. Everything that happens between those walls, she knows it, and if she has to : she'll punish you.", 50, room);
        myDialogue.setFirstMeet("Hello, welcome to your new school. I'll be your Principal, if you have any question, good luck! Here is the Map of the school, it will help you not getting lost. Have a nice day.\nIf you need something, I'll be in my office all day long.");
        myDialogue.setQuestStart("You've got nothing to do in my office. You better get out as soon as possible !");
        myDialogue.setQuestComplete("Sorry I don't have time to talk I'm with another student.");
        myDialogue.setAfterQuest("If I catch the hooligan that did this, they are in big trouble!");
        myDialogue.setBeardTalk("The parent-teacher reunion is next week I'm affraid.");
    }

    @Override
    public void talk(Hero hero){
        if (super.getRoom().equals(game.TheatreClass)){
            this.isQuestCompleted = true;
        }
        if (super.getRoom().equals(game.FirstFloor)){
            this.isAfterQuest = true;
        }
        if (hero.getHasFakeBeard()){
            System.out.println(myDialogue.getBeardTalk());
        }
        else if (isAfterQuest){
            System.out.println(myDialogue.getAfterQuest());
        }
        else if (isQuestCompleted){
            System.out.println(myDialogue.getQuestComplete());
        }
        else if (isQuestStarted){
            System.out.println(myDialogue.getQuestStart());
        }
        else{
            System.out.println(myDialogue.getFirstMeet());
            super.give("Map", hero);
            System.out.println("*" + super.getName() + " gives you the map*");
            this.isQuestStarted = true;
            super.getRoom().getPeople().remove(this);
            super.setRoom(game.PrincipalOffice);
            super.setDescription("She's working on school's financials issues.");
            super.getRoom().addHuman(this);
        }
    }
}