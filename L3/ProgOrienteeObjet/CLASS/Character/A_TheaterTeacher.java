package CLASS.Character;

import CLASS.Room.*;
import CLASS.Game.Dialogue;
import CLASS.Game.TheGame;

public class A_TheaterTeacher extends Adult {
    private TheGame game = TheGame.getInstance();
    private Dialogue myDialogue = new Dialogue();

        
    public A_TheaterTeacher(Room room){
        super("Gavroche", "He's young for a teacher, looks like he could be a student here", 50, room);
        myDialogue.setFirstMeet("You've got nothing to do here !!!");
        myDialogue.setBeardTalk("Have you heard the exams papers just arrived from the Academy ? The Principal keeps them in her office for the moment.");
    }

    @Override
    public void talk(Hero hero){
        if (!hero.getHasFakeBeard()){
            System.out.println(myDialogue.getFirstMeet());
            hero.setRoom(game.FirstFloor);
            System.out.println("*" + super.getName() + " brings you out of the "+ super.getRoom().getName() + "*");
        }
        else{
            System.out.println(myDialogue.getFirstMeet());
        }
    }
}