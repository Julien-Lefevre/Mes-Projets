package CLASS.Character;

import CLASS.Game.Dialogue;
import CLASS.Game.TheGame;
import CLASS.Room.*;

public class A_PhilosophyTeacher extends Adult {
    private TheGame game = TheGame.getInstance();
    private Dialogue myDialogue = new Dialogue();

        
        public A_PhilosophyTeacher(Room room){
            super("Chidi", "He's the Philosophy Teacher, he is secretly sad because he thinks he will never find his soulmate.", 30, room);
            myDialogue.setFirstMeet("You've got nothing to do here !!!");
            myDialogue.setBeardTalk("Have you heard the exams papers just arrived from the Academy ? The Principal keeps them in her office for the moment.");
        }

        @Override
        public void talk(Hero hero){
            if (!hero.getHasFakeBeard()){
                System.out.println(myDialogue.getFirstMeet());
                hero.setRoom(game.FirstFloor);
                System.out.println("*"+super.getName() + " brings you out of the "+ super.getRoom().getName()+"*");
            }
            else System.out.println(myDialogue.getBeardTalk());
        }
}