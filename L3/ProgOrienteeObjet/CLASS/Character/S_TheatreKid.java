package CLASS.Character;

import CLASS.Game.Dialogue;
import CLASS.Game.TheGame;
import CLASS.Room.*;

public class S_TheatreKid extends Student {
    private TheGame game = TheGame.getInstance();
    private Dialogue myDialogue = new Dialogue();
    private Boolean isQuestStarted = false;
    private Boolean isQuestCompleted = false;
    private Boolean isAfterQuest = false;

        
        public S_TheatreKid(Room room){
            super("Martoni", "He loves everything about theater and overall Drama, his dream is to became a world famous actor.", 10, room);
            myDialogue.setFirstMeet("Hello there ! I heard somewhere that you are trying to steal the ExamSubject. I'm usually not into illegal things but I need a favor from you. I'll help with your venture if you put this LoveLetter into " + game.ArtKid.getName() + "'s locker.");
            myDialogue.setQuestStart("Have you put my LoveLetter in her locker yet? They're in the Hall.");
            myDialogue.setQuestComplete("Thank you. With your help, the burning love i feel for thy princess will finally come to light. I'll distract the principal for you.");
            myDialogue.setAfterQuest("Go ahead, i'll keep her here.");
            myDialogue.setBeardTalk("Not a bad FakeBeard, a little DIY if you ask me.");
        }

        @Override
        public void talk(Hero hero){
        if (isQuestStarted && !hero.searchInBag("LoveLetter")){
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
                game.Principal.setRoom(hero.getRoom());
                game.FirstFloor.addHuman(game.Principal);
                this.isAfterQuest = true;
            }
            else if (isQuestStarted){
                System.out.println(myDialogue.getQuestStart());
            }
            else{
                System.out.println(myDialogue.getFirstMeet());
                this.give("LoveLetter", hero);
                this.isQuestStarted = true;
            }
        }
}
